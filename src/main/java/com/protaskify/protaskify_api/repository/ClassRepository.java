package com.protaskify.protaskify_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.protaskify.protaskify_api.model.enity.Class;

public interface ClassRepository extends JpaRepository<Class, String> {
}
