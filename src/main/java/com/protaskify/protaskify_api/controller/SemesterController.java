package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.exception.ResourceNotFoundException;
import com.protaskify.protaskify_api.model.enity.Semester;
import com.protaskify.protaskify_api.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/semesters")
public class SemesterController {

    @Autowired
    private SemesterRepository semesterRepository;

    @GetMapping
    public List<Semester> getAllSemesters() {
        return semesterRepository.findAll();
    }

    //build create semester REST API
    @PostMapping
    public Semester createSemester(@RequestBody Semester semester) {
        return semesterRepository.save(semester);
    }

    //build get Semester by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Semester> getSemesterById(@PathVariable long id){
        Semester semester = semesterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Semester not exist with Id: " + id));
        return ResponseEntity.ok(semester);
    }

    //build update Semester REST API note FE: Just update when Semester has not been started
    @PutMapping("{id}")
    public ResponseEntity<Semester> updateSemester(@PathVariable long id,@RequestBody Semester semesterDetail){
        Semester updateSemester = semesterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Semester not exist with Id: " + id));

        updateSemester.setStartDate(semesterDetail.getStartDate());
        updateSemester.setEndDate(semesterDetail.getEndDate());
//        updateSemester.setStatus(semesterDetail.getStatus());
        semesterRepository.save(updateSemester);
        return ResponseEntity.ok(updateSemester);
    }

    //build Delete "MayBe"
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteSemester(@PathVariable long id){
        Semester semester = semesterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Semester not exist with Id: " + id));

        semesterRepository.delete(semester);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
