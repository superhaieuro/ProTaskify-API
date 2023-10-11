package com.protaskify.protaskify_api.repository;


import com.protaskify.protaskify_api.model.enity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
    //all crud database methods

}
