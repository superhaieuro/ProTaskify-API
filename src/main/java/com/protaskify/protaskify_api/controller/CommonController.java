package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.enity.Messages;
import com.protaskify.protaskify_api.model.enity.Semester;
import com.protaskify.protaskify_api.repository.MessagesRepository;
import com.protaskify.protaskify_api.repository.StudentRepository;
import com.protaskify.protaskify_api.service.MessageService;
import com.protaskify.protaskify_api.service.SemesterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        String toId = messages.getLecturerId().toString();
        if (messages.getFromId().equals(messages.getLecturerId())){
            toId = messages.getStudentId().toString();
        }
        simpMessagingTemplate.convertAndSendToUser(toId,"/topic/room", messages);
        simpMessagingTemplate.convertAndSend(messageService.saveMessageFromJSON(messages), messages);
    }

//    @PostMapping("/get-param")
//    public PaginationRequest getParam (@RequestBody int pageNo, @RequestBody int pageSize){
//        PaginationRequest paginationRequest = new PaginationRequest();
//        paginationRequest.setPageNo(pageNo);
//        paginationRequest.setPageSize(pageSize);
//        return  paginationRequest;
//    }

    @GetMapping("/message-detail")
    public ResponseEntity<List<Messages>> getMessage(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize, @RequestParam("studentId") String studentId){
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("date").descending());
        Page<Messages> messagesList = messagesRepository.findMessageByStudentId(studentId, pageable);
        return ResponseEntity.ok(messagesList.getContent());
    }

    @GetMapping("/message-list")
    public ResponseEntity<List<?>> getLeader(@RequestParam("studentId") String studentId){
        return ResponseEntity.ok(studentRepository.getMessagesInfo(studentId));
    }

    @GetMapping("/get-active-semester")
    public ResponseEntity<Semester> getActiveSemester() {
        return ResponseEntity.ok(semesterService.getActiveSemester());
    }
}
