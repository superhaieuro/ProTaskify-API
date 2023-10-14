package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.enity.Semester;
import com.protaskify.protaskify_api.model.request.ImportStudentListRequest;
import com.protaskify.protaskify_api.service.LecturerService;
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

    @PostMapping("/import-student")
    public void importStudent(@RequestBody ImportStudentListRequest request) {
        lecturerService.saveStudentList(request);
    }

    @GetMapping("/get-lecturer-semester")
    public ResponseEntity<List<Semester>> getLecturerSemester(@PathParam("lecturerId") String lecturerId) {
        return ResponseEntity.ok(lecturerService.getSemesterByLecturer(lecturerId));
    }
}
