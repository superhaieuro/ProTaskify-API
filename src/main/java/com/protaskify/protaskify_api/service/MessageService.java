package com.protaskify.protaskify_api.service;

import com.protaskify.protaskify_api.model.enity.Messages;
import com.protaskify.protaskify_api.repository.MessagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessagesRepository messagesRepository;

    public String saveMessageFromJSON(Messages messages){
        messagesRepository.save(messages);
        return messages.getContent();
    }
}
