package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<Semester, Long> {

}
