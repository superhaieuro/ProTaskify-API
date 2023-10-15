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
    private final ProjectRepository projectRepository;
    private final StudentRepository studentRepository;

    public Task createTask (Task task, String studentId){
//        Student student = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            if (student != null && student.isLeader()){
                Set<Student> studentList = new HashSet<>();
                studentList.add(student);
                Long featureId = task.getFeature().getId();
                Feature feature = featureRepository.getSpecialFeature(featureId);
                task.setFeature(feature);
                task.setStudent(studentList);
                List<Task> taskList = taskRepository.getTaskByStatus(featureId, task.getStatus());
                task.setTaskIndex(taskList.size() + 1);
                return taskRepository.save(task);
            }
        }
        return null;
    }

    public Task updateTask (Task updatedTask, String studentId){
//        Student student = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            if (student != null && student.isLeader()) {
                Group group = student.getGroup();
                Long projectId = group.getId();
                Feature feature = featureRepository.getSpecialFeature(projectId);
                updatedTask.setFeature(feature);
                Task existingTask = taskRepository.findById(updatedTask.getId()).orElse(null);
                if (existingTask != null) {
                    BeanUtils.copyProperties(updatedTask, existingTask, "id");
                }
                return taskRepository.save(existingTask);
            }
        }
        return null;
    }

    public void deleteTask (Long taskId, String studentId){
//        Student student = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            if (student != null && student.isLeader()) {
                Task existingTask = taskRepository.findById(taskId).orElse(null);
                if (existingTask != null) {
                    taskRepository.delete(existingTask);
                }
            }
        }
    }

    public List<Task> getTaskByStatus (Long featureId, String status){
        List<Task> taskList = taskRepository.getTaskByStatus(featureId, status);
        return taskList;
    }
}
