package com.protaskify.protaskify_api.model.request;

import com.protaskify.protaskify_api.model.enity.Student;
import lombok.Data;

import java.util.List;

@Data
public class ImportStudentListRequest {
    private String classId;
    private String lecturerEmail;
    private List<Student> students;
}
