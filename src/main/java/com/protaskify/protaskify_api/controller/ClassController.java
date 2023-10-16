package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.enity.Class;
import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.repository.ClassRepository;
import com.protaskify.protaskify_api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/class")
@CrossOrigin
public class ClassController {
    private final ClassRepository classRepository;

    @GetMapping("/getall")
    public ResponseEntity<List<Class>> getAll(){
        List<Class> classes = classRepository.findAll();
        return ResponseEntity.ok(classes);
    }
}
