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

    public Student updateStudentInfo(String studentId, String facebook, String github, String skills) {
        Student existingStudent = studentRepository.findById(studentId).orElse(null);
        if (existingStudent != null) {
            existingStudent.setFacebook(facebook);
            existingStudent.setGithub(github);
            existingStudent.setSkills(skills);
            return studentRepository.save(existingStudent);
        }
        return null;
    }
}
