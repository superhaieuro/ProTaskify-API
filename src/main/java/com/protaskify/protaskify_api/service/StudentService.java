package com.protaskify.protaskify_api.service;

import com.protaskify.protaskify_api.model.enity.Group;
import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentService {
private final StudentRepository studentRepository;
    public List<Student> getGroupMembers() {
        Student student = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (student != null) {
            Group group = student.getGroup();
            if (group != null) {
                Long groupId = group.getId();
                Long classId = group.getClasses().getId();
                return studentRepository.findStudentByGroupId(classId, groupId);
            }
        }
        return Collections.emptyList();
    }
}
