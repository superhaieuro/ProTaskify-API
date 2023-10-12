package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findAllByEmail(String email);

    @Query(value = "SELECT * FROM [dbo].[student] where class_id = :classID",nativeQuery = true)
    List<Student> getStudentByClass(int classID);

    @Query(
            value = "select student_id, is_leader from student where is_leader = true and class_id = ?",
            nativeQuery = true)
    List<Student> findAllLeader();
}
