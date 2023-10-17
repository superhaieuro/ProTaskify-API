package com.protaskify.protaskify_api.service;

import com.protaskify.protaskify_api.model.enity.*;
import com.protaskify.protaskify_api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class FeatureService {
    private final FeatureRepository featureRepository;
    private final StudentRepository studentRepository;
    private final TaskRepository taskRepository;

    public Feature createFeature(Feature feature) {
        Student student = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (student != null && student.isLeader()) {
            Group group = student.getGroup();
            if (group != null ) {
                feature.setStartDate(feature.getStartDate());
                feature.setEndDate(feature.getEndDate());
                feature.setGroup(group);
                return featureRepository.save(feature);
            }
        }
        return null;
    }

    public Feature updateFeature(Long featureId, Feature updatedFeature) {
        Student student = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (student != null && student.isLeader()) {
            Feature existingFeature = featureRepository.findById(featureId).orElse(null);
            if (existingFeature != null) {
                Group group = student.getGroup();
                if (group != null) {
                    if (group.getClasses().getId().equals(existingFeature.getGroup().getClasses().getId())) {
                        existingFeature.setGroup(group);
                        existingFeature.setName(updatedFeature.getName());
                        existingFeature.setStatus(updatedFeature.isStatus());
                        existingFeature.setDescription(updatedFeature.getDescription());
                        existingFeature.setStartDate(updatedFeature.getStartDate());
                        existingFeature.setEndDate(updatedFeature.getEndDate());
                        return featureRepository.save(existingFeature);
                    }
                }
            }
        }
        return null;
    }

    public void deleteFeature(Long featureId) {
        Student student = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (student != null && student.isLeader()) {
            Feature existingFeature = featureRepository.findById(featureId).orElse(null);
            if (existingFeature != null) {
                Group group = student.getGroup();
                if (group != null) {
                    if (group.getClasses().getId().equals(existingFeature.getGroup().getClasses().getId())) {
                        featureRepository.delete(existingFeature);
                    }
                }
            }
        }
    }

    public List<Feature> getAllFeatures(Long classId, Long groupId) {
        return featureRepository.findByClassIdAndGroupId(classId, groupId);
    }

    public List<Feature> getGroupFeatures() {
        Student student = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (student != null) {
            Group group = student.getGroup();
            if (group != null ) {
                Long groupId = group.getId();
                Long classId = group.getClasses().getId();
                return featureRepository.findByClassIdAndGroupId(classId,groupId);
            }
        }
        return Collections.emptyList();
    }

    public void setStatusFeature (Task updatedTask){
        Long featureId = updatedTask.getFeature().getId();
        List<Task> taskList = taskRepository.getTaskList(featureId);
        String status = "Done";
        for (Task task: taskList) {
            if (!task.getStatus().equalsIgnoreCase("Done")){
                status = "To do";
            }
        }
        if (status.equalsIgnoreCase("Done")) {
            Feature feature = featureRepository.getSpecialFeature(featureId);
            feature.setStatus(true);
            featureRepository.save(feature);
        }
    }
}