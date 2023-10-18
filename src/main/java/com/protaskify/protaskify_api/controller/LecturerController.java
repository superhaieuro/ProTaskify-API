package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.request.ImportStudentListRequest;
import com.protaskify.protaskify_api.service.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
