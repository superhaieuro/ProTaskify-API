package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.enity.Feature;
import com.protaskify.protaskify_api.model.enity.Task;
import com.protaskify.protaskify_api.service.student.FeatureService;
import com.protaskify.protaskify_api.service.student.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final FeatureService featureService;

    @PostMapping("/create-task/{studentId}")
    public ResponseEntity<Task> createTask(@RequestBody Task task, @PathVariable String studentId) {
        try {
            Task createTask = taskService.createTask(task, studentId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createTask);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/update-task/{studentId}")
    public ResponseEntity<Task> updateTask (@RequestBody Task updatedTask, @PathVariable String studentId) {
        try {
            Task editedTask = taskService.updateTask(updatedTask, studentId);
            featureService.setStatusFeature(updatedTask);
            return ResponseEntity.ok(editedTask);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/delete-task/{taskId}/{studentId}")
    public ResponseEntity<Task> deleteTask (@PathVariable Long taskId, @PathVariable String studentId) {
        try {
            taskService.deleteTask(taskId, studentId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
