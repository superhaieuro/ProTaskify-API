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
public class StudentTaskId implements Serializable {
    private static final long serialVersionUID = 5648702655548007482L;
    @Column(name = "student_id", nullable = false, length = 50)
    private String studentId;

    @Column(name = "task_id", nullable = false, length = 50)
    private String taskId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StudentTaskId entity = (StudentTaskId) o;
        return Objects.equals(this.studentId, entity.studentId) &&
                Objects.equals(this.taskId, entity.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, taskId);
    }

}
