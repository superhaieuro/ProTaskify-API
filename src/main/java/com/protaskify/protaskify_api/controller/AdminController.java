package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.enity.Lecturer;
import com.protaskify.protaskify_api.model.enity.Semester;
import com.protaskify.protaskify_api.model.request.ImportLecturerRequest;
import com.protaskify.protaskify_api.model.request.ImportProjectListRequest;
import com.protaskify.protaskify_api.service.LecturerService;
import com.protaskify.protaskify_api.service.ProjectService;
import com.protaskify.protaskify_api.service.SemesterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin")
@CrossOrigin
public class AdminController {
    private final SemesterService semesterService;
    private final LecturerService lecturerService;
    private final ProjectService projectService;

    //CREATE SEMESTER
    @PostMapping("/create-semester")
    public void createSemester(@RequestBody Semester semester) {
        semesterService.saveSemester(semester);
    }

    //DISPLAY ALL SEMESTER
    @GetMapping("/view-all-semesters")
    public List<Semester> getAllSemesters() {
        return semesterService.findAllSemester();
    }

    //GET SEMESTER BY ID
    @GetMapping("/view-semester-by-id/{semester_id}")
    public ResponseEntity<Semester> getSemesterById(@PathVariable Long semester_id) {
        try {
            Semester semester = semesterService.finSemesterId(semester_id);
            return ResponseEntity.ok(semester);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //UPDATE SEMESTER Note FE: Just update when Semester has not been started
    @PutMapping("/update-semester/{semester_id}")
    public ResponseEntity<Semester> updateSemester(@PathVariable Long semester_id, @RequestBody Semester semesterDetail) {
        try {
            Semester updateSemester = semesterService.updateSemester(semester_id, semesterDetail);
            return ResponseEntity.ok(updateSemester);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    //BUILD DELETE MAY BE
    @DeleteMapping("/delete-semester/{semester_id}")
    public ResponseEntity<Semester> deleteSemester(@PathVariable Long semester_id) {
        semesterService.deleteSemester(semester_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //IMPORT LECTURE
    @PostMapping("/import-lecturers")
    public void importLecturer(@RequestBody ImportLecturerRequest request) {
        lecturerService.saveLecturerList(request);
    }

    //DISPLAY ALL LECTURE
    @GetMapping("/view-all-lecturers")
    public List<Lecturer> getAllLecturers() {
        return lecturerService.findAllLecturer();
    }

    //GET LECTURE BY ID
    @GetMapping("/view-lecturer-by-id/{lecturer_id}")
    public ResponseEntity<Lecturer> getLecturerById(@PathVariable String lecturer_id) {
        try {
            Lecturer lecturer = lecturerService.findLecturerId(lecturer_id);
            return ResponseEntity.ok(lecturer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //UPDATE LECTURE
    @PutMapping("/update-lecturer/{lecturer_id}")
    public ResponseEntity<Lecturer> updateLecturer(@PathVariable String lecturer_id, @RequestBody Lecturer lecturerDetails) {
        try {
            Lecturer updateLecturer = lecturerService.updateLecturer(lecturer_id, lecturerDetails);
            return ResponseEntity.ok(updateLecturer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/import-project")
    public void importProject(@RequestBody ImportProjectListRequest request) {
        projectService.saveProjectList(request);
    }
}
