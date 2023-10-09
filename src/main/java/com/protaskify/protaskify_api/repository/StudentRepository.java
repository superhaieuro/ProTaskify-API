package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findAllByEmail(String email);

    @Query(
            value = "SELECT m.student_id, m.content, m.date, g.group_name " +
                    "FROM Messages m " +
                    "JOIN ( " +
                    "    SELECT student_id, MAX(date) as MaxDate " +
                    "    FROM Messages " +
                    "    GROUP BY student_id " +
                    ") latestMsg ON m.student_id = latestMsg.student_id AND m.date = latestMsg.MaxDate " +
                    "join student s on s.student_id = m.student_id " +
                    "join groups g on g.class_id = s.class_id " +
                    "where g.class_id = :classId", nativeQuery = true)
    List<?> getMessagesInfo(int classId);

}
