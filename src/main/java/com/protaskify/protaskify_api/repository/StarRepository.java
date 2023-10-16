package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Star;
import com.protaskify.protaskify_api.model.enity.StarId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarRepository extends JpaRepository<Star, StarId> {
}
