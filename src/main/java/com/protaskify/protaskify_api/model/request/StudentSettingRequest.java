package com.protaskify.protaskify_api.model.request;

import lombok.Data;

@Data
public class StudentSettingRequest {
    private String studentId;
    private String facebook;
    private String github;
    private String skill;
}
