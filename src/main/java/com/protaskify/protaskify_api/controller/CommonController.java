package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.enity.*;
import com.protaskify.protaskify_api.model.request.SendMessageRequest;
import com.protaskify.protaskify_api.repository.LecturerRepository;
import com.protaskify.protaskify_api.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.protaskify.protaskify_api.repository.MessagesRepository;
import com.protaskify.protaskify_api.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1/common")
public class CommonController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageService messageService;
    private final SemesterService semesterService;
    private final FeatureService featureService;
    private final TaskService taskService;
    private final GroupService groupService;
    private final ProjectService projectService;
    private final SprintService sprintService;

    private final MessagesRepository messagesRepository;
    private final StudentRepository studentRepository;
    private final LecturerRepository lecturerRepository;


    //--------------------Common--------------------
    @GetMapping("/get-active-semester")
    public ResponseEntity<Semester> getActiveSemester() {
        return ResponseEntity.ok(semesterService.getActiveSemester());
    }


    //--------------------Message--------------------
    @MessageMapping("/room")
    public void sendMessage(@RequestBody SendMessageRequest request) {
        Messages messages = Messages.builder()
                .content(request.getContent())
                .lecturer(lecturerRepository.findById(request.getLecturerId()).get())
                .student(studentRepository.findById(request.getStudentId()).get())
                .date(request.getDate())
                .fromId(request.getFromId())
                .status(false)
                .build();
        String toId = request.getLecturerId();
        if (request.getFromId().equals(request.getStudentId())) {
            toId = request.getStudentId();
        }
        simpMessagingTemplate.convertAndSendToUser(toId, "/topic/room", messages);
        simpMessagingTemplate.convertAndSend(messageService.saveMessageFromJSON(messages), messages);
    }

    @GetMapping("/message-detail")
    public ResponseEntity<List<Messages>> getMessage(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize,
                                                     @RequestParam("studentId") String studentId, @RequestParam("lecturerId") String lecturerId) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("date").descending());
        Page<Messages> messagesList = messagesRepository.findByStudentIdAndLecturerId(studentId, lecturerId, pageable);
        return ResponseEntity.ok(messagesList.getContent());
    }

    @GetMapping("/message-list")
    public ResponseEntity<List<?>> getMessagesInfo(@RequestParam("lecturerId") String lecturerId) {
        String semesterId = semesterService.getActiveSemester().getId();
        return ResponseEntity.ok(studentRepository.getMessagesInfo(semesterId, lecturerId));
    }


    //--------------------Feature--------------------
    @GetMapping("/view-features")
    public ResponseEntity<List<Feature>> getAllFeatures(@RequestParam("userId") String userId, @RequestParam("role") String role,
                                                        @RequestParam(name = "classId", required = false) Long classId,
                                                        @RequestParam(name = "groupId", required = false) Long groupId) {
        try {
            List<Feature> groupFeatures;
            if (role.equals("STUDENT")) {
                Student student = studentRepository.findStudentById(userId);
                groupFeatures = featureService.getAllFeatures(student.getClasses().getId(), student.getGroup().getId());
            } else {
                groupFeatures = featureService.getAllFeatures(classId, groupId);
            }
            return ResponseEntity.ok(groupFeatures);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    //--------------------Task--------------------
    @GetMapping("/view-all-task-of-group")
    public ResponseEntity<List<Task>> getTasksByGroup(@RequestParam("userId") String userId, @RequestParam("role") String role,
                                                      @RequestParam(name = "classId", required = false) Long classId,
                                                      @RequestParam(name = "groupId", required = false) Long groupId) {
        try {
            List<Task> tasks;
            if (role.equals("STUDENT")) {
                Student student = studentRepository.findStudentById(userId);
                tasks = taskService.getAllTasksOfGroup(student.getClasses().getId(), student.getGroup().getId());
            } else {
                tasks = taskService.getAllTasksOfGroup(classId, groupId);
            }
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    //--------------------Group--------------------
    @GetMapping("/get-group")
    public ResponseEntity<Group> getGroup(@RequestParam("userId") String userId, @RequestParam("role") String role,
                                          @RequestParam(name = "classId", required = false) Long classId,
                                          @RequestParam(name = "groupId", required = false) Long groupId) {
        try {
            Group group;
            if (role.equals("STUDENT")) {
                Student student = studentRepository.findStudentById(userId);
                group = groupService.getGroup(student.getGroup().getId());
            } else {
                group = groupService.getGroup(groupId);
            }
            return ResponseEntity.ok(group);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    //--------------------Project--------------------
    @GetMapping("/get-all-topic")
    public ResponseEntity<List<Project>> getAllActiveTopic() {
        return ResponseEntity.ok(projectService.getAllProjectByStatus(true));
    }


    //--------------------Sprint--------------------
    @GetMapping("/view-sprint/{classId}")
    public ResponseEntity<List<Sprint>> getSprintsByClass(@PathVariable Long classId) {
        List<Sprint> sprints = sprintService.getAllSprintsByClass(classId);
        return ResponseEntity.ok(sprints);
    }


    //--------------------Student--------------------
    @GetMapping("/without-group/{classId}")
    public ResponseEntity<List<Student>> getStudentsWithNullGroupId(@PathVariable Long classId) {
        List<Student> students = studentRepository.findStudentsWithNullGroupId(classId);
        return ResponseEntity.ok(students);
    }
}

