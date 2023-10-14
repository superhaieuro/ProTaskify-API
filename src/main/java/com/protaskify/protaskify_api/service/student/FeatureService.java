package com.protaskify.protaskify_api.service.student;
import com.protaskify.protaskify_api.model.enity.*;
import com.protaskify.protaskify_api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FeatureService {
    private final FeatureRepository featureRepository;
    private final ProjectRepository projectRepository;


    public Feature createFeature(Feature feature) {
        Student student = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (student != null && student.is_leader()) {
            Group group = student.getGroupId();
            Long projectId = projectRepository.findProjectIdByGroupId(group.getId());
            Project project = projectRepository.findById(projectId).orElse(null);
            if (project != null && project.getGroupId().getId().equals(group.getId())) {
                feature.setProject(project);
                feature.setStart_date(feature.getStart_date());
                feature.setEnd_date(feature.getEnd_date());
            }
        }
        return featureRepository.save(feature);
    }

    public Feature updateFeature(Long featureId, Feature updatedFeature){
        Student student = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (student != null && student.is_leader()) {
            Feature existingFeature = featureRepository.findById(featureId).orElse(null);
            if (existingFeature != null) {
                Group group = student.getGroupId();
                Long projectId = projectRepository.findProjectIdByGroupId(group.getId());
                Project project = existingFeature.getProject();
                if (project != null && project.getId().equals(projectId)) {
                    existingFeature.setName(updatedFeature.getName());
                    existingFeature.setStatus(updatedFeature.isStatus());
                    existingFeature.setDescription(updatedFeature.getDescription());
                    existingFeature.setStart_date(updatedFeature.getStart_date());
                    existingFeature.setEnd_date(updatedFeature.getEnd_date());
                    return featureRepository.save(existingFeature);
                }
            }
        }
        return updatedFeature;
    }

    public void deleteFeature(Long featureId) {
        Student student = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (student != null && student.is_leader()) {
            Feature existingFeature = featureRepository.findById(featureId).orElse(null);
            if (existingFeature != null) {
                Group group = student.getGroupId();
                Long projectId = projectRepository.findProjectIdByGroupId(group.getId());
                Project project = existingFeature.getProject();
                if (project != null && project.getId().equals(projectId)) {
                    featureRepository.delete(existingFeature);
                }
            }
        }
    }

    public List<Feature> getAllFeatures(Long classId, Long groupId) {
        return featureRepository.findByClassIdAndGroupId(classId, groupId);
    }
}