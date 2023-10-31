package com.protaskify.protaskify_api.model.request;

import ch.qos.logback.core.status.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class SendMessageRequest {
    private String senderId;
    private String receiverId;
    private String message;
    private Date date;
    private boolean status;
}
