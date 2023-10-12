package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
    @Query(
            value = "select * from feature f " +
                    "where f.project_id = :projectId",
            nativeQuery = true)
    List<Feature> getFeatureList (Long projectId);

    @Query(
            value = "select * from feature f " +
                    "where f.feature_id = :featureId",
            nativeQuery = true)
    Feature getSpecialFeature (Long featureId);

//    @Query(
//            value = "select * from feature f " +
//                    "where f.feature_id = :featureId",
//            nativeQuery = true)
//    Feature getStatusFeature (Long featureId);
}