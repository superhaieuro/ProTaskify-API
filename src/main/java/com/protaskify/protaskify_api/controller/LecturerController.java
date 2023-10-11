package com.protaskify.protaskify_api.controller;


import com.protaskify.protaskify_api.exception.ResourceNotFoundException;
import com.protaskify.protaskify_api.model.enity.Lecturer;
import com.protaskify.protaskify_api.repository.LecturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/lecturers")
@RequiredArgsConstructor
public class LecturerController {

    @Autowired
    private LecturerRepository lecturerRepository;
//    private final LecturerRepository lecturerRepository;

    @GetMapping
    public List<Lecturer> getAllLecturers() {
        return lecturerRepository.findAll();
    }

    //build create lecture REST API
    @PostMapping
    public Lecturer createLecturer(@RequestBody Lecturer lecturer) {
        return lecturerRepository.save(lecturer);
    }

    // build get lecture by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Lecturer> getLecturerById(@PathVariable long id) {
        Lecturer lecturer = lecturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lecturer not exist with ID: " + id));
        return ResponseEntity.ok(lecturer);

    }

    //build update lecturer REST API
    @PutMapping("{id}")
    public ResponseEntity<Lecturer> updateLecturer(@PathVariable long id, @RequestBody Lecturer lecturerDetails) {
        Lecturer updateLecturer = lecturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lecturer not exist with ID: " + id));

        updateLecturer.setName(lecturerDetails.getName());
        updateLecturer.setEmail(lecturerDetails.getEmail());
        //        updateLecturer.setStatus(lecturerDetails.getStatus());

        lecturerRepository.save(updateLecturer);

        return ResponseEntity.ok(updateLecturer);
    }

    //build delete lecturer REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteLecturer(@PathVariable long id){

        Lecturer lecturer = lecturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lecturer not exist with ID: " +id));
        lecturerRepository.delete(lecturer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}

