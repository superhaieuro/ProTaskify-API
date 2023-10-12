package com.protaskify.protaskify_api.service.student;
import com.protaskify.protaskify_api.model.enity.*;
import com.protaskify.protaskify_api.model.enity.Process;
import com.protaskify.protaskify_api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeatureService {
    private final FeatureRepository featureRepository;
    private final ProjectRepository projectRepository;
    private final ProcessRepository processRepository;
    private final StudentRepository studentRepository;

    private final TaskRepository taskRepository;

    public Feature createFeature(Feature feature) {
        Student student = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (student != null && student.is_leader()) {
            Group group = student.getGroupId();
            Long projectId = projectRepository.findProjectIdByGroupId(group.getId());
            System.out.println(projectId);
            Project project = projectRepository.findById(projectId).orElse(null);
            if (project != null && project.getGroupId().getId().equals(group.getId())) {
                feature.setProject(project);
            }
        }
        return featureRepository.save(feature);
    }

    public Feature updateFeature(Long featureId, Feature updatedFeature) throws Exception {
        Student student = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (student != null && student.is_leader()) {
            Feature existingFeature = featureRepository.findById(featureId).orElse(null);
            if (existingFeature != null) {
                Group group = student.getGroupId();
                Long projectId = projectRepository.findProjectIdByGroupId(group.getId());
                Project project = existingFeature.getProject();
                if (project != null && project.getId().equals(projectId)) {
                    existingFeature.setName(updatedFeature.getName());
                    existingFeature.setStatus(updatedFeature.getStatus());
                    existingFeature.setDescription(updatedFeature.getDescription());
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

    public List<Feature> getFeatureList (String studentId){
//        Student student = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            if (student.is_leader()) {
                Group group = student.getGroupId();
                Long projectId = group.getId();
                List<Feature> listFeature = featureRepository.getFeatureList(projectId);
                return listFeature;
            }
        }
        return null;
    }

    public void setStatusFeature (Task updatedTask){
        Long featureId = updatedTask.getFeatureId().getId();
        List<Task> taskList = taskRepository.getTaskList(featureId);
        String status = "Done";
        for (Task task: taskList) {
            if (!task.getStatus().equalsIgnoreCase("Done")){
                status = "To do";
            }
        }
        if (status.equalsIgnoreCase("Done")) {
            Feature feature = featureRepository.getSpecialFeature(featureId);
            feature.setStatus("Done");
            featureRepository.save(feature);
        }
    }

}