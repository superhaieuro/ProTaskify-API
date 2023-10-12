package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, String> {
}
