package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.enity.Messages;
import com.protaskify.protaskify_api.model.enity.Semester;
import com.protaskify.protaskify_api.service.MessageService;
import com.protaskify.protaskify_api.service.SemesterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1/common")
public class CommonController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageService messageService;

    private final SemesterService semesterService;

    @MessageMapping("/message")
    public void sendMessage(Messages messages){
        simpMessagingTemplate.convertAndSendToUser(messages.getToId(),"/topic/room", messages);
        simpMessagingTemplate.convertAndSend(messageService.saveMessageFromJSON(messages), messages);
    }

    @GetMapping("/get-active-semester")
    public ResponseEntity<Semester> getActiveSemester() {
        return ResponseEntity.ok(semesterService.getActiveSemester());
    }
}
