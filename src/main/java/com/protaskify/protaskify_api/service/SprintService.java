package com.protaskify.protaskify_api.service;

import com.protaskify.protaskify_api.model.enity.*;
import com.protaskify.protaskify_api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SprintService {
    private final SprintRepository sprintRepository;
    private final StudentRepository studentRepository;
    private final ClassesRepository classesRepository;

    public Sprint findLatestSprintByStudentId(String studentId) {
        Student student = studentRepository.findStudentById(studentId);
        if (student != null) {
            Sprint sprint = sprintRepository.findSprintByStudentId(studentId);
            if (sprint != null && sprint.getClasses().getId().equals(student.getClasses().getId())) {
                return sprint;
            }
        }
        return null;
    }

    public List<Sprint> findSprintListByStudentId(String studentId) {
        Student student = studentRepository.findStudentById(studentId);
        if (student != null) {
            List<Sprint> sprint = sprintRepository.findSprintListByStudentId(studentId);
            if (sprint != null) {
                return sprint;
            }
        }
        return null;
    }

    public Sprint createSprint(Long classId, Sprint sprint) {
        Classes existingClass = classesRepository.findById(classId).orElse(null);
        if (existingClass != null) {
            sprint.setClasses(existingClass);
            return sprintRepository.save(sprint);
        }
        return null;
    }

    public Sprint updateSprint(Long sprintId, Sprint sprintUpdates) {
        Sprint existingSprint = sprintRepository.findById(sprintId).orElse(null);
        if (existingSprint != null) {
            existingSprint.setName(sprintUpdates.getName());
            existingSprint.setStartDate(sprintUpdates.getStartDate());
            existingSprint.setEndDate(sprintUpdates.getEndDate());
            existingSprint.setNote(sprintUpdates.getNote());
            return sprintRepository.save(existingSprint);
        }
        return null;
    }

    public List<Sprint> getAllSprintsByClass(Long classId) {
        return sprintRepository.findByClassesId(classId)
                .stream()
                .sorted(Comparator.comparing(Sprint::getEndDate, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }
}