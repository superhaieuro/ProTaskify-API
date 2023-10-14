package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}