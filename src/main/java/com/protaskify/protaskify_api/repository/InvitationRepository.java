package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Integer> {
}
