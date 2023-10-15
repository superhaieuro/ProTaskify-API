package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.enity.Messages;
import com.protaskify.protaskify_api.model.enity.Semester;
import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.repository.MessagesRepository;
import com.protaskify.protaskify_api.repository.StudentRepository;
import com.protaskify.protaskify_api.service.MessageService;
import com.protaskify.protaskify_api.service.SemesterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1/common")
public class CommonController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageService messageService;
    private final SemesterService semesterService;
    private  final MessagesRepository messagesRepository;
    private final StudentRepository studentRepository;

    @MessageMapping("/room")
    public void sendMessage(Messages messages){
        String toId = messages.getLecturer().toString();
        if (messages.getFromId().equals(messages.getLecturer())){
            toId = messages.getStudent().toString();
        }
        simpMessagingTemplate.convertAndSendToUser(toId,"/topic/room", messages);
        simpMessagingTemplate.convertAndSend(messageService.saveMessageFromJSON(messages), messages);
    }

    @GetMapping("/message-list")
    public List<Messages> getMessage(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize){
        List<Messages> messages = new ArrayList<>();
        messages.addAll(messagesRepository.findAll(PageRequest.of(pageNo, pageSize)).getContent());
        return messages;
    }

//    @GetMapping("/leader-list")
//    public List<Student> getLeader(){
//        return studentRepository.findAllLeader();
//    }

    @GetMapping("/get-active-semester")
    public ResponseEntity<Semester> getActiveSemester() {
        return ResponseEntity.ok(semesterService.getActiveSemester());
    }
}
