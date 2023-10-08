package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Feature;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.protaskify.protaskify_api.model.enity.Process;

@Repository
public interface ProcessRepository extends JpaRepository<Process, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Process p SET p.featureId = ?1")
    void updateFeatureId(Feature feature);
}