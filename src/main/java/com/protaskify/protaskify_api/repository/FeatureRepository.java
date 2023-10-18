package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature, String> {
}
