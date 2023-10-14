package com.protaskify.protaskify_api.model.request;

import lombok.Data;

@Data
public class PaginationRequest {
    int pageNo;
    int pageSize;
    String studentId;
}
