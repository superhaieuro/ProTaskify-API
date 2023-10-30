package com.protaskify.protaskify_api.service;

import com.protaskify.protaskify_api.model.enity.Project;
import com.protaskify.protaskify_api.model.request.ImportProjectListRequest;
import com.protaskify.protaskify_api.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    public List<Project> getAllProjectByStatus(boolean status) {
        return projectRepository.findAllByStatus(status);
    }

    public void saveProjectList(ImportProjectListRequest request) {
        List<Project> projects = request.getProjects();
        projects.forEach(project -> project.setStatus(true));
        projectRepository.saveAll(projects);
    }
}
