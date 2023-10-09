package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Feature;
import com.protaskify.protaskify_api.model.enity.Project;
import com.protaskify.protaskify_api.model.enity.Sprint;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.protaskify.protaskify_api.model.enity.Process;

@Repository
public interface ProcessRepository extends JpaRepository<Process, Long> {
}