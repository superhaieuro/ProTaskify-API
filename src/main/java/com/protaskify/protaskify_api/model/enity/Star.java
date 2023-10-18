package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "star")
public class Star {
    @EmbeddedId
    private StarId id;

    @MapsId("studentReviewedId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_reviewed_id", nullable = false)
    private Student studentReviewed;

    @MapsId("sprintId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sprint_id", nullable = false)
    private Sprint sprint;

    @MapsId("studentReviewerId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_reviewer_id", nullable = false)
    private Student studentReviewer;

    @Column(name = "hard_working")
    private Double hardWorking;

    @Column(name = "good_knowledge")
    private Double goodKnowledge;

    @Column(name = "team_working")
    private Double teamWorking;

    @Column(name = "total")
    private Double total;

    @Column(name = "respone_note", length = 100)
    private String responeNote;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "change_status_time")
    private OffsetDateTime changeStatusTime;

}
