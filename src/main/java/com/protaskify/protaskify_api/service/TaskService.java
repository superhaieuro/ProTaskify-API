package com.protaskify.protaskify_api.service;

import com.protaskify.protaskify_api.model.enity.Group;
import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.model.enity.Task;
import com.protaskify.protaskify_api.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> getAllTasksOfGroup() {
        Student student = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (student != null) {
            Group group = student.getGroup();
            if (group != null) {
                Long groupId = group.getId();
                Long classId = group.getClasses().getId();
                return taskRepository.findAllTasksOfGroup(classId, groupId);
            }
        }
        return Collections.emptyList();
    }

    public List<Task> getTasksByFeature(Long featureId) {
    Student student = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (student != null) {
            Group group = student.getGroup();
            if (group != null) {
                Long classId = group.getClasses().getId();
                Long groupId = group.getId();
                return taskRepository.findAllTasksOfFeature(classId, groupId, featureId);
            }
        }
        return Collections.emptyList();
    }
}
