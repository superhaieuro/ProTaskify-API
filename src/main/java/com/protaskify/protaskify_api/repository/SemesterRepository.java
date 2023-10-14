package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Lecturer;
import com.protaskify.protaskify_api.model.enity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, String> {
    Optional<Semester> findAllByStatus(Boolean status);

    @Query("SELECT s FROM Semester s JOIN s.classesList c JOIN c.studentList WHERE c.lecturer = :lecturer ORDER BY s.startDate DESC")
    List<Semester> findByLecturerClasses(Lecturer lecturer);
}
