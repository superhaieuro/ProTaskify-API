package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(
            "SELECT t FROM Task t"+
                    " JOIN t.feature f"+
                    " ON t.feature.id = f.id"+
                    " JOIN f.group g"+
                    " ON f.group.id = g.id"+
                    " WHERE g.classes.id = :classId AND g.id = :groupId"
    )
    List<Task> findAllTasksOfGroup(Long classId,Long groupId);

    @Query(
            "SELECT t FROM Task t " +
                    "WHERE  t.feature.id = :featureId"
    )
    List<Task> findAllTasksOfFeature(Long featureId);

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
