package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.enity.Message;
import com.protaskify.protaskify_api.service.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1/message")
public class MessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    private final MessageService messageService;

    @MessageMapping("/chatbox")
    public void sendMessage(Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getToId(),"/topic/room", message);
        simpMessagingTemplate.convertAndSend(messageService.saveMessageFromJSON(message), message);
    }
}
