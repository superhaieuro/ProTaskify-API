package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
