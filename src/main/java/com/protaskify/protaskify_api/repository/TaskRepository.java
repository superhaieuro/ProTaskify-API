package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(
            value = "select * from task t " +
                    "where t.feature_id = :featureId",
            nativeQuery = true)
    List<Task> getTaskList (Long featureId);

    @Query(
            value = "select * from task t " +
                    "where t.task_id = :taskId",
            nativeQuery = true)
    Task getTask (Long taskId);

    @Query(
            value = "select * from task t " +
                    "where t.feature_id = :featureId and t.status = :status " +
                    "order by t.task_index asc",
            nativeQuery = true)
    List<Task> getTaskByStatus (Long featureId, String status);
}
