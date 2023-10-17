package com.protaskify.protaskify_api.service;

import com.protaskify.protaskify_api.model.enity.*;
import com.protaskify.protaskify_api.repository.FeatureRepository;
import com.protaskify.protaskify_api.repository.ProjectRepository;
import com.protaskify.protaskify_api.repository.StudentRepository;
import com.protaskify.protaskify_api.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final FeatureRepository featureRepository;
    private final TaskRepository taskRepository;
    private final StudentRepository studentRepository;


    public Task createTask(Task task, String studentId, Long featureId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            if (student != null) {
//                Set<Student> studentList = new HashSet<>();
//                studentList.add(student);
//                Long featureId = task.getFeature().getId();
                Feature feature = featureRepository.getSpecialFeature(featureId);
                task.setFeature(feature);
                task.setStudent(student);
                List<Task> taskList = taskRepository.getTasksByStatus(featureId, task.getStatus());
                task.setTaskIndex(taskList.size() + 1);
                return taskRepository.save(task);
            }
        }
        return null;
    }

    public Task updateTask(Task updatedTask, String studentId, Long featureId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            if (student != null && student.isLeader()) {
                Feature feature = featureRepository.getSpecialFeature(featureId);
                updatedTask.setFeature(feature);
                updatedTask.setStudent(student);
                Task existingTask = taskRepository.getTask(updatedTask.getId());
                if (updatedTask.getStatus().equals(existingTask.getStatus())) {

                    List<Task> taskList = taskRepository.getTasksByStatus(
                            featureId,
                            updatedTask.getStatus());
                    Iterator<Task> iterator = taskList.iterator();
                    while (iterator.hasNext()) {
                        Task task = iterator.next();
//                        task.setFeature(feature);
                        if (task.equals(existingTask)) {
                            iterator.remove();
                        } else if (task.getTaskIndex() < existingTask.getTaskIndex() && task.getTaskIndex() >= updatedTask.getTaskIndex()) {
                            task.setTaskIndex(task.getTaskIndex() + 1);
                        } else if (task.getTaskIndex() <= updatedTask.getTaskIndex() && task.getTaskIndex() > 1) {
                            task.setTaskIndex(task.getTaskIndex() - 1);
                        }
                    }
                    taskList.add(updatedTask);
                    taskRepository.saveAll(taskList);
                } else {
                    List<Task> newStatusTaskList = taskRepository.getTasksByStatus(
                            featureId,
                            updatedTask.getStatus());
                    updatedTask.setTaskIndex(newStatusTaskList.size() + 1);
                    newStatusTaskList.add(updatedTask);

                    List<Task> taskList = taskRepository.getTasksByStatus(
                            featureId,
                            existingTask.getStatus());
                    Task oldTask = taskRepository.getTask(existingTask.getId());
                    taskList.remove(oldTask);
                    Iterator<Task> iterator = taskList.iterator();
                    while (iterator.hasNext()) {
                        Task task = iterator.next();
                        if (task.getTaskIndex() > oldTask.getTaskIndex()) {
                            task.setTaskIndex(task.getTaskIndex() - 1);
                        }
                    }
                    taskRepository.saveAll(taskList);
                    taskRepository.saveAll(newStatusTaskList);
                }
            }
        }
        return null;
    }

    public void deleteTask(Long taskId, String studentId, Long featureId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            if (student != null && student.isLeader()) {
                Feature feature = featureRepository.getSpecialFeature(featureId);
                Task existingTask = taskRepository.findById(taskId).orElse(null);
                existingTask.setFeature(feature);
                if (existingTask != null) {
                    List<Task> taskList = taskRepository.getTasksByStatus(
                            featureId,
                            existingTask.getStatus());
                    Task oldTask = taskRepository.getTask(existingTask.getId());
                    taskList.remove(oldTask);
                    Iterator<Task> iterator = taskList.iterator();
                    while (iterator.hasNext()) {
                        Task task = iterator.next();

                        if (task.getTaskIndex() > existingTask.getTaskIndex()) {
                            task.setTaskIndex(task.getTaskIndex() - 1);
                        }
                    }
                    taskRepository.delete(existingTask);
                }
            }
        }
    }

    public List<Task> getTasksByStatus (Long featureId, String status) {
        List<Task> taskList = taskRepository.getTasksByStatus(featureId, status);
        return taskList;
    }

    public List<Task> getAllTasksOfGroup(Long classId, Long groupId) {
        return taskRepository.findAllTasksOfGroup(classId, groupId);
    }
}
