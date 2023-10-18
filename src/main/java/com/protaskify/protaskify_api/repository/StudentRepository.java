package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findAllByEmail(String email);

    @Query(value = "Select Student from Student where classField.classId = :id")
    List<Student> findStudentByClass(String id);

    @Query(value = "Select Student from Student where group.groupId = :id")
    List<Student> findStudentByGroup(String id);
}
