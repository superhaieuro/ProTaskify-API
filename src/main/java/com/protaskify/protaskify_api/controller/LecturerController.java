package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.enity.*;
import com.protaskify.protaskify_api.model.request.ImportStudentListRequest;
import com.protaskify.protaskify_api.repository.ClassesRepository;
import com.protaskify.protaskify_api.service.LecturerService;
import com.protaskify.protaskify_api.service.SprintService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/lecturer")
@CrossOrigin
public class LecturerController {
    private final LecturerService lecturerService;
    private final ClassesRepository classesRepository;
    private final SprintService sprintService;


    //--------------------Student--------------------
    @PostMapping("/import-student")
    public void importStudent(@RequestBody ImportStudentListRequest request) {
        lecturerService.saveStudentList(request);
    }

    @PutMapping("/update-student-info")
    public ResponseEntity<String> updateStudentInfo(
            @RequestParam("studentId") String studentId,
            @RequestParam("groupId") Long groupId,
            @RequestParam(value = "score", required = false) Double score
    ) {
        boolean updated = lecturerService.updateStudentInfo(studentId, groupId, score);
        if (updated) {
            return ResponseEntity.ok("Student information updated successfully.");
        } else {
            return ResponseEntity.badRequest().body("Student not found with the given studentId.");
        }
    }


    //--------------------Semester--------------------
    @GetMapping("/get-lecturer-semester")
    public ResponseEntity<List<Semester>> getLecturerSemester(@PathParam("lecturerEmail") String lecturerEmail) {
        return ResponseEntity.ok(lecturerService.getSemesterByLecturer(lecturerEmail));
    }


    //--------------------Class--------------------
    @GetMapping("/get-class/{classId}")
    public ResponseEntity<Classes> getClass(@PathVariable("classId") Long classId) {
        return ResponseEntity.ok(classesRepository.findById(classId).get());
    }


    //--------------------Sprint--------------------
    @PostMapping("/create-sprint/{classId}")
    public ResponseEntity<Sprint> createSprint(@PathVariable Long classId, @RequestBody Sprint sprint) {
        Sprint newSprint = sprintService.createSprint(classId, sprint);
        if (newSprint != null) {
            return ResponseEntity.ok(newSprint);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update-sprint/{sprintId}")
    public ResponseEntity<Sprint> updateSprint(
            @PathVariable Long sprintId,
            @RequestBody Sprint sprintUpdates
    ) {
        Sprint updatedSprint = sprintService.updateSprint(sprintId, sprintUpdates);
        if (updatedSprint != null) {
            return ResponseEntity.ok(updatedSprint);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    //--------------------Feedback--------------------
    @PutMapping("/update-feedback")
    public ResponseEntity<Feedback> updateFeedback(
            @RequestParam Long groupId,
            @RequestParam Long feedbackId,
            @RequestParam String feedbackText
    ) {
        Feedback feedback = lecturerService.updateFeedback(groupId, feedbackId, feedbackText);
        if (feedback != null) {
            return ResponseEntity.ok(feedback);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    //--------------------Group--------------------
    @PostMapping("/create-group")
    public ResponseEntity<Group> createGroup(
            @RequestParam Long classId,
            @RequestParam String groupName,
            @RequestParam List<String> studentListWithOutGroup,
            @RequestParam String leaderId
    ) {
        Group newGroup = lecturerService.createGroup(classId, groupName, studentListWithOutGroup, leaderId);
        if (newGroup != null) {
            return ResponseEntity.ok(newGroup);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete-group")
    public ResponseEntity<String> deleteGroupOfClass(
            @RequestParam Long groupId,
            @RequestParam Long classId
    ) {
        return lecturerService.deleteGroupOfClass(groupId, classId);
    }
}
