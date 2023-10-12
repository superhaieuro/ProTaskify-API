package com.protaskify.protaskify_api.service.student;

import com.protaskify.protaskify_api.model.enity.Group;
import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getStudentsInGroup (String studentId){
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()){
            Group group =  studentOptional.get().getGroupId();
            List<Student> studentList = studentRepository.findByGroupId(group.getId());
            return studentList;
        }
        return null;
    }
}
