package com.protaskify.protaskify_api.repository;
import com.protaskify.protaskify_api.model.enity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT p.id FROM Project p JOIN Group g ON p.id =g.project.id")
    Long findProjectIdByGroupId(Long groupId);
}