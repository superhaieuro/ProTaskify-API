package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Class, String> {
}
