package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.enity.*;
import com.protaskify.protaskify_api.service.FeatureService;
import com.protaskify.protaskify_api.service.SprintService;
import com.protaskify.protaskify_api.service.StudentService;
import com.protaskify.protaskify_api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final FeatureService featureService;
    private final SprintService sprintService;
    private final StudentService studentService;
    private final TaskService taskService;

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

    @GetMapping("/view-feature-member-list")
    public ResponseEntity<Object> getFeatureAndStudent() {
        try {
            List<Feature> groupFeatures = featureService.getGroupFeatures();
            List<Student> groupMembers = studentService.getGroupMembers();
            Map<String, Object> response = new HashMap<>();
            response.put("groupFeatures", groupFeatures);
            response.put("groupMembers", groupMembers);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/view-all-task-of-group")
    public ResponseEntity<List<Task>> getTasksByGroup() {
        try {
            List<Task> tasks = taskService.getAllTasksOfGroup();
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/view-task-of-each-feature/{featureId}")
    public ResponseEntity<List<Task>> getTasksOfEachFeature(@PathVariable Long featureId) {
        try {
            List<Task> tasks = taskService.getTasksByFeature(featureId);
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
