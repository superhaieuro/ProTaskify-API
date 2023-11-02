package com.protaskify.protaskify_api.repository;

import com.protaskify.protaskify_api.model.enity.Invitation;
import com.protaskify.protaskify_api.model.enity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    @Query(
            value = "SELECT i FROM Invitation i WHERE i.student.id = :studentId AND i.status = false "
    )
    List<Invitation> findInvitationsByStudentIdAndStatus(String studentId);

    @Query(
            value = "SELECT i FROM Invitation i WHERE i.id = :invitationId"
    )
    Invitation findInvitationById(Long invitationId);

    @Query(
            value = "SELECT i FROM Invitation i WHERE i.group.id = :groupId AND i.student.id = :studentId AND i.status = false"
    )
    Invitation findInvitationByGroupIdAndStudentId(Long groupId, String studentId);
}
