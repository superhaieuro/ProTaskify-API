package com.protaskify.protaskify_api.service;

import com.protaskify.protaskify_api.model.enity.Classes;
import com.protaskify.protaskify_api.model.enity.Lecturer;
import com.protaskify.protaskify_api.model.enity.Semester;
import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.model.request.ImportStudentListRequest;
import com.protaskify.protaskify_api.repository.ClassesRepository;
import com.protaskify.protaskify_api.repository.LecturerRepository;
import com.protaskify.protaskify_api.repository.SemesterRepository;
import com.protaskify.protaskify_api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LecturerService {
    private final StudentRepository studentRepository;
    private final LecturerRepository lecturerRepository;
    private final ClassesRepository classesRepository;
    private final SemesterRepository semesterRepository;

    public void saveStudentList(ImportStudentListRequest request) {
        Lecturer lecturer = lecturerRepository.findAllByEmail(request.getLecturerEmail()).get();
        Semester semester = semesterRepository.findAllByStatus(true).get();
        Classes classes = Classes.builder()
                .id(request.getClassId())
                .lecturer(lecturer)
                .semester(semester)
                .build();
        classesRepository.save(classes);
        for (Student student : request.getStudents()) {
            student.setClasses(classes);
        }
        studentRepository.saveAll(request.getStudents());
    }
}