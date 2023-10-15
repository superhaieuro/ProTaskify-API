package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {

    @Query("SELECT s FROM Sprint s"+
            " JOIN Student st ON st.classes.id = s.classes.id"+
            " WHERE st.id= :studentId ORDER BY s.endDate DESC LIMIT 1")
    Sprint findSprintByStudentId(String studentId);
}
