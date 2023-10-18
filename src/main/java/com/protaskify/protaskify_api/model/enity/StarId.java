package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class StarId implements Serializable {
    private static final long serialVersionUID = -1232469442761115923L;
    @Column(name = "student_reviewed_id", nullable = false, length = 50)
    private String studentReviewedId;

    @Column(name = "sprint_id", nullable = false, length = 50)
    private String sprintId;

    @Column(name = "student_reviewer_id", nullable = false, length = 50)
    private String studentReviewerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StarId entity = (StarId) o;
        return Objects.equals(this.sprintId, entity.sprintId) &&
                Objects.equals(this.studentReviewedId, entity.studentReviewedId) &&
                Objects.equals(this.studentReviewerId, entity.studentReviewerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprintId, studentReviewedId, studentReviewerId);
    }

}
