package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.service.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/lecturer")
@CrossOrigin
public class LecturerController {
    private final LecturerService lecturerService;

    @PostMapping("/import-student")
    public void importStudent(@RequestBody List<Student> students) {
        lecturerService.saveStudentsFromJSON(students);
    }
}
