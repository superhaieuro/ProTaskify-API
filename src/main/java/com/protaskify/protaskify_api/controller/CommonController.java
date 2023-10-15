package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.enity.Feature;
import com.protaskify.protaskify_api.service.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.protaskify.protaskify_api.model.enity.Messages;
import com.protaskify.protaskify_api.model.enity.Semester;
import com.protaskify.protaskify_api.model.request.SendMessageRequest;
import com.protaskify.protaskify_api.repository.MessagesRepository;
import com.protaskify.protaskify_api.repository.StudentRepository;
import com.protaskify.protaskify_api.service.MessageService;
import com.protaskify.protaskify_api.service.SemesterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1/common")
public class CommonController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageService messageService;
    private final SemesterService semesterService;
    private final MessagesRepository messagesRepository;
    private final StudentRepository studentRepository;
    private final FeatureService featureService;

    //--------------------Common--------------------
    @GetMapping("/get-active-semester")
    public ResponseEntity<Semester> getActiveSemester() {
        return ResponseEntity.ok(semesterService.getActiveSemester());
    }


    //--------------------Message--------------------
    @MessageMapping("/room")
    public void sendMessage(@RequestBody SendMessageRequest request) {
        Messages messages = messageService.getMessageInfo(request);
        String toId = messages.getLecturer().getId();
        if (messages.getFromId().equals(messages.getLecturer().getId())) {
            toId = messages.getStudent().toString();
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

