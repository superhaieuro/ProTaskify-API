package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.ProcessId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessRepository extends JpaRepository<Process, ProcessId> {
}
