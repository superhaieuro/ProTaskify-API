package com.protaskify.protaskify_api.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SendMessageRequest {
    private String content;
    private String lecturerId;
    private String studentId;
    private Date date;
    private String fromId;
    private Boolean status;
}
