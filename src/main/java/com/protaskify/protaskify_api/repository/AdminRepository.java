package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findAllByEmail(String email);
}
