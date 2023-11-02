package com.protaskify.protaskify_api.model.response;

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
    private boolean isInGroup = false;
    private boolean groupStatus = false;
}
