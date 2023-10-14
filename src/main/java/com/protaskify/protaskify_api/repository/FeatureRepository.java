package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
    @Query("SELECT f FROM Feature f " +
            "JOIN f.project p " +
            "JOIN p.groupId g " +
            "WHERE g.classes.id = :classId AND g.id = :groupId")
    List<Feature> findByClassIdAndGroupId(Long classId, Long groupId);
}