package com.protaskify.protaskify_api.model.request;

import com.protaskify.protaskify_api.model.enity.Lecturer;
import lombok.Data;

import java.util.List;

@Data
public class ImportLecturerRequest {
    private List<Lecturer> lecturers;
}
