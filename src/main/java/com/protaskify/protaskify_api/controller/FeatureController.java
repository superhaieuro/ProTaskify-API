package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.enity.Feature;
import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.service.student.FeatureService;
import com.protaskify.protaskify_api.service.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class FeatureController {
    private final FeatureService featureService;
    private final StudentService studentService;

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

    @GetMapping("/feature-list")
    public ResponseEntity<List<Feature>> getFeatureList (@RequestParam("studentId") String studentId){
        return ResponseEntity.ok(featureService.getFeatureList(studentId));
    }

    @GetMapping("/student-list")
    public ResponseEntity<List<Student>> getStudentsInGroup (@RequestParam("studentId") String studentId){
        return ResponseEntity.ok(studentService.getStudentsInGroup(studentId));
    }

//    @PutMapping("/update-staus-feature/{featureId}")
//    public ResponseEntity<Feature> updateStatusFeature (@PathVariable Long featureId) {
//        try {
//            Feature statusFeature = featureService.setStatusFeature(featureId);
//            return ResponseEntity.ok(statusFeature);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }
}
