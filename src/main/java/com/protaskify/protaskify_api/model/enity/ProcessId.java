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
public class ProcessId implements Serializable {
    private static final long serialVersionUID = 3165951760012704221L;
    @Column(name = "project_id", nullable = false, length = 50)
    private String projectId;

    @Column(name = "sprint_id", nullable = false, length = 50)
    private String sprintId;

    @Column(name = "feature_id", nullable = false, length = 50)
    private String featureId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProcessId entity = (ProcessId) o;
        return Objects.equals(this.sprintId, entity.sprintId) &&
                Objects.equals(this.projectId, entity.projectId) &&
                Objects.equals(this.featureId, entity.featureId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprintId, projectId, featureId);
    }

}
