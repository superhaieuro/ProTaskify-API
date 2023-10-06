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
            value = "select s.student_id, g.group_name, m.date from student s" +
                    "join class c on s.class_id = c.class_id" +
                    "join groups g on c.class_id = g.class_id" +
                    "join messages m on s.student_id = m.student_id" +
                    "where s.student_id = :studentId",
            nativeQuery = true)
    List<Student> findAllLeader(String studentId);
}
