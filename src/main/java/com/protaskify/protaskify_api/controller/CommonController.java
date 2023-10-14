package com.protaskify.protaskify_api.controller;
import com.protaskify.protaskify_api.model.enity.Feature;
import com.protaskify.protaskify_api.service.student.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/common")
@RequiredArgsConstructor
public class CommonController {
    private final FeatureService featureService;

    @GetMapping("/view-features/{classId}/{groupId}")
    public ResponseEntity<List<Feature>> getAllFeatures(
             @PathVariable Long classId, @PathVariable Long groupId) {
        try {
            List<Feature> groupFeatures = featureService.getAllFeatures(classId, groupId);
            return ResponseEntity.ok(groupFeatures);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

