package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, String> {
    Optional<Semester> findAllByStatus(Boolean status);
}
