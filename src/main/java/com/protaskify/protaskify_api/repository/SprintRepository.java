package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Process;
import com.protaskify.protaskify_api.model.enity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {
}
