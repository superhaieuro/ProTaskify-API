package com.protaskify.protaskify_api.model.auth;

import com.protaskify.protaskify_api.model.enity.Lecturer;
import com.protaskify.protaskify_api.model.enity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private Object userInfo;
}
