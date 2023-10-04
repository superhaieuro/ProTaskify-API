package com.protaskify.protaskify_api.service;

import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LecturerService {
    private final StudentRepository studentRepository;

    public void saveStudentsFromJSON(List<Student> students) {
        studentRepository.saveAll(students);
    }
}
