package com.protaskify.protaskify_api.controller;
import com.protaskify.protaskify_api.service.student.FeatureService;
import com.protaskify.protaskify_api.model.enity.Feature;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class FeatureController {
    @Autowired
    private FeatureService featureService;
    @PostMapping("/create-feature")
    public Feature createFeature(HttpServletRequest request, @RequestBody Feature feature) throws Exception {
        String studentId = request.getParameter("studentId");
        return featureService.createFeature(feature, studentId);
    }
}