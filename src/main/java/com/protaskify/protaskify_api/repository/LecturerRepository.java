package com.protaskify.protaskify_api.repository;


import com.protaskify.protaskify_api.model.enity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface LecturerRepository extends JpaRepository<Lecturer, String> {
    Optional<Lecturer> findAllByEmail(String userEmail);
}
