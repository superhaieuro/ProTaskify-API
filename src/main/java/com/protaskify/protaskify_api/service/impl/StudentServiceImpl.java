package com.protaskify.protaskify_api.service.impl;

import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.repository.StudentRepository;
import com.protaskify.protaskify_api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getStudentsByClass(int classID) {
        return studentRepository.getStudentByClass(classID);
    }
}
