package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.service.lecturer.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import  java.util.HashMap;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/lecturer")
@CrossOrigin
public class LecturerController {
    private final LecturerService lecturerService;

    @PostMapping("/import-student")
    public List<String> importStudent(@RequestBody List<Student> students) {
        List<String> duplicateStudentId = findDuplicateStudentIds(students);
        if (!duplicateStudentId.isEmpty()) {
            return duplicateStudentId;
        }
        lecturerService.saveStudentsFromJSON(students);
        return duplicateStudentId;
    }

    private List<String> findDuplicateStudentIds(List<Student> students) {
        List<String> duplicateIds = students.stream()
                .collect(Collectors.groupingBy(Student::getId, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return duplicateIds;
    }

}
