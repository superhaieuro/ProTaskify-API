package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.enity.Feature;
import com.protaskify.protaskify_api.model.enity.Sprint;
import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.model.enity.Task;
import com.protaskify.protaskify_api.model.request.StudentSettingRequest;
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
@CrossOrigin
public class StudentController {
    private final FeatureService featureService;
    private final SprintService sprintService;
    private final StudentService studentService;
    private final TaskService taskService;


    //--------------------Sprint--------------------
    @GetMapping("/sprint/{studentId}")
    public ResponseEntity<Sprint> getSprints(@PathVariable String studentId) {
        try {
            Sprint sprint = sprintService.findLatestSprintByStudentId(studentId);
            return ResponseEntity.ok(sprint);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    //--------------------Feature--------------------
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


    //--------------------Task--------------------
    //method này để lấy ra 2 danh sách cho sinh viên chọn feature và member làm task
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

    @PostMapping("/create-task/{studentId}/{featureId}")
    public ResponseEntity<Task> createTask(@RequestBody Task task, @PathVariable String studentId,
                                           @PathVariable Long featureId) {
            Task createTask = taskService.createTask(task, studentId, featureId);
            return ResponseEntity.ok(createTask);
    }

    @PutMapping("/update-task/{studentId}/{featureId}")
    public ResponseEntity<Task> updateTask (@RequestBody Task updatedTask, @PathVariable String studentId,
                                            @PathVariable Long featureId) {
            Task editedTask = taskService.updateTask(updatedTask, studentId, featureId);
            featureService.setStatusFeature(updatedTask, featureId);
            return ResponseEntity.ok(editedTask);
    }

    @DeleteMapping("/delete-task/{taskId}/{studentId}/{featureId}")
    public ResponseEntity<Task> deleteTask (@PathVariable Long taskId, @PathVariable String studentId,
                                            @PathVariable Long featureId) {
            taskService.deleteTask(taskId, studentId, featureId);
            return ResponseEntity.noContent().build();
    }


    //--------------------Setting--------------------
    @PutMapping("/update-student-info")
    public ResponseEntity<Student> updateStudentInfo(@RequestBody StudentSettingRequest request) {
        try {
            Student updatedStudent = studentService.updateStudentInfo(request.getStudentId(), request.getFacebook(), request.getGithub(), request.getSkill());
            return ResponseEntity.ok(updatedStudent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    @GetMapping("/get-task/{featureId}/{status}")
//    public ResponseEntity<List<Task>> getTasksByStatus (@PathVariable Long featureId, @PathVariable String status) {
//        List<Task> getTasksByStatus = taskService.getTasksByStatus(featureId, status);
//        return ResponseEntity.ok(getTasksByStatus);
//    }
}
