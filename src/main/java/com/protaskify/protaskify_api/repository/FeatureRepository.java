package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {

}