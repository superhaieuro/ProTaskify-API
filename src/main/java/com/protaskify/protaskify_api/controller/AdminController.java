package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.request.ImportProjectListRequest;
import com.protaskify.protaskify_api.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin")
@CrossOrigin
public class AdminController {
private final ProjectService projectService;

    @PostMapping("/import-project")
    public void importLecturer(@RequestBody ImportProjectListRequest request) {
        projectService.saveProjectList(request);
    }
}
