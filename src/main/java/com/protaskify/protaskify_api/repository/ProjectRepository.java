package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, String> {
}
