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
            value = "select * from student s where s.student_id = :studentId",
            nativeQuery = true)
    Optional<Student> findById (String studentId);

    @Query(
            value = "select * from student s where s.group_id = :groupId",
            nativeQuery = true)
    List<Student> findByGroupId (Long groupId);
}
