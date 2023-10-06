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
            value = "select top 1 s.student_id, g.group_name, m.content, m.date from student s " +
                    "join class c on s.class_id = c.class_id " +
                    "join groups g on g.class_id = c.class_id " +
                    "join messages m on s.student_id = m.from_id " +
                    "where s.student_id = :studentId " +
                    "order by m.date desc", nativeQuery = true)
    List<?> getMessagesInfo(String studentId);

}
