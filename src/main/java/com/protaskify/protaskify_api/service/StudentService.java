package com.protaskify.protaskify_api.service;

import com.protaskify.protaskify_api.model.enity.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getStudentsByClass(int classID);
}
