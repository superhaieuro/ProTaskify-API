package com.protaskify.protaskify_api.service.student;
import com.protaskify.protaskify_api.model.enity.*;
import com.protaskify.protaskify_api.model.enity.Process;
import com.protaskify.protaskify_api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeatureService {
    private final FeatureRepository featureRepository;
    private final ProjectRepository projectRepository;


    public Feature createFeature(Feature feature) throws Exception {
        Student student = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (student != null && student.is_leader()) {
            Group group = student.getGroupId();
            Long projectId = projectRepository.findProjectIdByGroupId(group.getId());
            System.out.println(projectId);
            Project project = projectRepository.findById(projectId).orElse(null);
            if (project != null && project.getGroupId().getId().equals(group.getId())) {
                feature.setProject(project);
                return featureRepository.save(feature);
            } else {
                throw new Exception("Project không thuộc nhóm của bạn.");
            }
        } else {
            throw new Exception("Chỉ leader của nhóm mới có thể tạo feature.");
        }
    }
}