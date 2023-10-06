package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Messages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagesRepository extends JpaRepository<Messages, Integer>  {

    @Query(
            value = "select * from messages m where m.from_id like %:studentId%",
            nativeQuery = true)
    Page<Messages> findMessageByStudentId(String studentId, Pageable pageable);
}
