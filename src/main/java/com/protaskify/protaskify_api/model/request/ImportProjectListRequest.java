package com.protaskify.protaskify_api.model.request;

import com.protaskify.protaskify_api.model.enity.Project;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ImportProjectListRequest {
    private List<Project> projects;
}
