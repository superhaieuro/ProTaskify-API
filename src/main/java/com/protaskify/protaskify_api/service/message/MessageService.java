package com.protaskify.protaskify_api.service.message;

import com.protaskify.protaskify_api.model.enity.Message;
import com.protaskify.protaskify_api.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public String saveMessageFromJSON(Message message){
        messageRepository.save(message);
        return message.getContent();
    }
}
