package com.protaskify.protaskify_api.service.lecturer;

import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LecturerService {
    private final StudentRepository studentRepository;

    public void saveStudentsFromJSON(List<Student> students) {
        studentRepository.saveAll(students);
    }
}
