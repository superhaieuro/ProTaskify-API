package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.enity.Feature;
import com.protaskify.protaskify_api.model.enity.Sprint;
import com.protaskify.protaskify_api.service.FeatureService;
import com.protaskify.protaskify_api.service.SprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final FeatureService featureService;
    private final SprintService sprintService;

    @PostMapping("/create-feature")
    public ResponseEntity<Feature> createFeature(@RequestBody Feature feature) {
        try {
            Feature createdFeature = featureService.createFeature(feature);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdFeature);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/update-feature/{featureId}")
    public ResponseEntity<Feature> updateFeature(@PathVariable Long featureId, @RequestBody Feature updatedFeature) {
        try {
            Feature editedFeature = featureService.updateFeature(featureId, updatedFeature);
            return ResponseEntity.ok(editedFeature);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/delete-feature/{featureId}")
    public ResponseEntity<Void> deleteFeature(@PathVariable Long featureId) {
        try {
            featureService.deleteFeature(featureId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/sprint/{studentId}")
    public ResponseEntity<Sprint> getSprints(@PathVariable String studentId) {
        try {
            Sprint sprint = sprintService.findLatestSprintByStudentId(studentId);
            return ResponseEntity.ok(sprint);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
