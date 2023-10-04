package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Messages, Integer> {
}
