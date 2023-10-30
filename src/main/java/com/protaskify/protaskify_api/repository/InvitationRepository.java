package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Invitation;
import com.protaskify.protaskify_api.model.enity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    @Query(
            value = "SELECT * FROM student WHERE student_id = :studentId", nativeQuery = true
    )
    List<Invitation> findInvitationsByStudentId(String studentId);

    @Query(
            value = "SELECT * FROM student WHERE invitation_id = :invitationId", nativeQuery = true
    )
    Invitation findInvitationById(Long invitationId);
}
