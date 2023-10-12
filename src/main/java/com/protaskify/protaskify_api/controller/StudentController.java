package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/student")
@CrossOrigin
public class StudentController {
    private final StudentRepository studentRepository;
    @GetMapping("/getStudentsByClass")
    public ResponseEntity<List<Student>> getStudentsByClass(@RequestParam("classid") Integer classID){
        return ResponseEntity.ok(studentRepository.getStudentByClass(classID));
    }
}
