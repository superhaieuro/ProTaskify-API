package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {
}
