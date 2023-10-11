package com.protaskify.protaskify_api.repository;


import com.protaskify.protaskify_api.model.enity.LecturerGG;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface LecturerGGRepository extends JpaRepository<LecturerGG, Long> {
    Optional<LecturerGG> findAllByEmail(String userEmail);
    //all crud database methods

}
