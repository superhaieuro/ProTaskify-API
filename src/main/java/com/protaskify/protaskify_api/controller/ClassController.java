package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.repository.ClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.protaskify.protaskify_api.model.enity.Class;

import java.util.List;
@CrossOrigin(value = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/class")
public class ClassController {
    private final ClassRepository classRepository;

    @GetMapping("/getall")
    public ResponseEntity<List<Class>> getAll(){
        List<Class> classes = classRepository.findAll();
        return ResponseEntity.ok(classes);
    }
}
