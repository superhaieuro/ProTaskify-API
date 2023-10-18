package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(
            "SELECT t FROM Task t JOIN Student st ON t.student = st WHERE st.group.id = :groupId AND st.classes.id = :classId"
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
            value = "select t from Task t " +
//                    "join student s on t.student_id = s.student_id " +
                    "where (t.feature.id = :featureId or t.student.group.id = :groupId) and t.status = :status " +
                    "order by t.taskIndex asc")
    List<Task> getTasksByStatus (Long featureId, Long groupId, String status);

    @Modifying
    @Query("UPDATE Task t SET t.feature = null WHERE t.feature.id = :featureId")
    void updateFeatureIdForFeatureTasks(Long featureId);

//    @Query(
//            value = "select t from task t " +
//                    "join t.student s on s.student_id = t.student_id " +
//                    "where s.group_id = :groupId or t.feature_id = :featureId " +
//                    "order by t.task_index asc",
//            nativeQuery = true)
//    List<Task> findAllTasksByGroupOrFeature (Long groupId, Long featureId);
}
