package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.exception.ResourceNotFoundException;
import com.protaskify.protaskify_api.model.enity.Lecturer;
import com.protaskify.protaskify_api.model.enity.Semester;
import com.protaskify.protaskify_api.repository.SemesterRepository;
import com.protaskify.protaskify_api.service.semester.SemesterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/semesters")
@RequiredArgsConstructor
public class SemesterController {

    private final SemesterService semesterService;

    //CREATE SEMESTER
    @PostMapping
    public Semester createSemester(@RequestBody Semester semester) {
        return semesterService.saveSemester(semester);
    }

    //DISPLAY ALL SEMESTER
    @GetMapping("/view-all-semesters")
    public List<Semester> getAllSemesters() {
        return semesterService.findAllSemester();
    }

    //GET SEMESTER BY ID
    @GetMapping("/view-semester-by-id/{semester_id}")
    public ResponseEntity<Semester> getSemesterById(@PathVariable String semester_id){
        try {
            Semester semester = semesterService.finSemesterId(semester_id);
            return  ResponseEntity.ok(semester);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    //UPDATE SEMESTER Note FE: Just update when Semester has not been started
    @PutMapping("/update-semester/{semester_id}")
    public ResponseEntity<Semester> updateSemester(@PathVariable String semester_id,@RequestBody Semester semesterDetail){
        try {
            Semester updateSemester = semesterService.updateSemester(semester_id, semesterDetail);
            return ResponseEntity.ok(updateSemester);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    //BUILD DELETE MAY BE
    @DeleteMapping("/delete-semester/{semester_id}")
    public ResponseEntity<Semester> deleteSemester(@PathVariable String semester_id){
        semesterService.deleteSemester(semester_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
