package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository extends JpaRepository<Sprint, String> {
}
