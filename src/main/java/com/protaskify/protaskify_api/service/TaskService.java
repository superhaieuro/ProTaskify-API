package com.protaskify.protaskify_api.service;

import com.protaskify.protaskify_api.model.enity.Task;
import com.protaskify.protaskify_api.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> getAllTasksOfGroup(Long classId, Long groupId) {
                return taskRepository.findAllTasksOfGroup(classId, groupId);
            }

    public List<Task> getTasksByFeature(Long featureId) {
                return taskRepository.findAllTasksOfFeature(featureId);
            }
}
