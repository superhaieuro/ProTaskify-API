package com.protaskify.protaskify_api.service;

import com.protaskify.protaskify_api.model.enity.*;
import com.protaskify.protaskify_api.model.request.ImportStudentListRequest;
import com.protaskify.protaskify_api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LecturerService {
    private final StudentRepository studentRepository;
    private final LecturerRepository lecturerRepository;
    private final ClassesRepository classesRepository;
    private final SemesterRepository semesterRepository;
    private final FeedbackRepository feedbackRepository;
    private final GroupRepository groupRepository;
    private final SprintRepository sprintRepository;


    public void saveStudentList(ImportStudentListRequest request) {
        Lecturer lecturer = lecturerRepository.findAllByEmail(request.getLecturerEmail()).get();
        Semester semester = semesterRepository.findAllByStatus(true).get();
        Classes classes = Classes.builder()
                .name(request.getClassName())
                .lecturer(lecturer)
                .semester(semester)
                .build();
        classesRepository.save(classes);
        for (Student student : request.getStudents()) {
            student.setClasses(classes);
        }
        studentRepository.saveAll(request.getStudents());
    }

    public List<Semester> getSemesterByLecturer(String lecturerEmail) {
        Lecturer lecturer = lecturerRepository.findAllByEmail(lecturerEmail).get();
        List<Semester> semesterList = semesterRepository.findByLecturerClasses(lecturer);
        return semesterList;
    }

    public boolean updateStudentInfo(String studentId, Long groupId, Double score) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            if (groupId == 0) {
                student.setGroup(null);
            } else {
                Group newGroup = new Group();
                newGroup.setId(groupId);
                student.setGroup(newGroup);
            }
            if (score != null) {
                student.setScore(score);
            }
            studentRepository.save(student);
            return true;
        }
        return false;
    }


    public Feedback createFeedback(Long sprintId, Long groupId, String feedbackText) {
        Sprint sprint = sprintRepository.findById(sprintId).orElse(null);
        Group group =  groupRepository.findById(groupId).orElse(null);
        if (sprint != null && group != null) {
            Feedback feedback = new Feedback();
            feedback.setSprint(sprint);
            feedback.setGroup(group);
            feedback.setFeedback(feedbackText);
            return feedbackRepository.save(feedback);
        }
        return null;
    }

    public Feedback updateFeedback(Long groupId,Long feedbackId, String feedbackText) {
        Group group = groupRepository.findById(groupId).orElse(null);
        Feedback feedback = feedbackRepository.findById(feedbackId).orElse(null);

        if (group != null && feedback != null) {
            feedback.setFeedback(feedbackText);
            return feedbackRepository.save(feedback);
        }

        return null;
    }


    public Group createGroup(Long classId, String groupName, List<String> studentListWithOutGroup, String leaderId) {
        Classes classes = classesRepository.findById(classId).orElse(null);
        if (classes != null) {
            Group newGroup = new Group();
            newGroup.setName(groupName);
            newGroup.setClasses(classes);
            newGroup = groupRepository.save(newGroup);

            if (!studentListWithOutGroup.isEmpty()) {
                List<Student> studentsToAdd = studentRepository.findAllById(studentListWithOutGroup);
                for (Student student : studentsToAdd) {
                    student.setGroup(newGroup); // Gán nhóm mới cho từng sinh viên
                }
                studentRepository.saveAll(studentsToAdd); // Lưu danh sách sinh viên
                newGroup.setStudentList(studentsToAdd);
            }

            Student leader = studentRepository.findById(leaderId).orElse(null);
            if (leader != null) {
                leader.setLeader(true);
                studentRepository.save(leader); // Lưu thông tin leader
            }
            return newGroup;
        }
        return null;
    }


    public ResponseEntity<String> deleteGroupOfClass(Long groupId, Long classId) {
        Group group = groupRepository.findById(groupId).orElse(null);
        Classes classes = classesRepository.findById(classId).orElse(null);

        if (group != null && classes != null) {
            List<Student> studentsInGroup = group.getStudentList();

            for (Student student : studentsInGroup) {
                student.setGroup(null);
                student.setLeader(false);
            }
            studentRepository.saveAll(studentsInGroup);
            groupRepository.deleteById(groupId);
        }
        return null;
    }
}