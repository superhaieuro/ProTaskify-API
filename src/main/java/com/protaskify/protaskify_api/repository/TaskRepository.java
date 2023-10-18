package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, String> {
}
