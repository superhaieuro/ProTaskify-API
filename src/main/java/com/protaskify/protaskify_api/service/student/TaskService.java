package com.protaskify.protaskify_api.service.student;

import com.protaskify.protaskify_api.model.enity.*;
import com.protaskify.protaskify_api.repository.FeatureRepository;
import com.protaskify.protaskify_api.repository.ProjectRepository;
import com.protaskify.protaskify_api.repository.StudentRepository;
import com.protaskify.protaskify_api.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
            if (student != null && student.is_leader()){
                Group group = student.getGroupId();
                Long projectId = group.getId();
                Feature feature = featureRepository.getSpecialFeature(projectId);
                task.setFeatureId(feature);
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
            if (student != null && student.is_leader()) {
                Group group = student.getGroupId();
                Long projectId = group.getId();
                Feature feature = featureRepository.getSpecialFeature(projectId);
                updatedTask.setFeatureId(feature);
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
            if (student != null && student.is_leader()) {
                Task existingTask = taskRepository.findById(taskId).orElse(null);
                if (existingTask != null) {
                    taskRepository.delete(existingTask);
                }
            }
        }
    }
}
