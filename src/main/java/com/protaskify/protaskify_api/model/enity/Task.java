package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "task")
public class Task {
    @Id
    @Column(name = "task_id", nullable = false, length = 50)
    private String taskId;

    @Column(name = "task_name", length = 50)
    private String taskName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feature_id")
    private Feature feature;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "feedback", length = 100)
    private String feedback;

    @Column(name = "prority", length = 50)
    private String prority;

    @Column(name = "description")
    private byte[] description;

    @Column(name = "create_date")
    private OffsetDateTime createDate;

    @Column(name = "finish_date")
    private OffsetDateTime finishDate;

    @OneToMany(mappedBy = "task")
    private Set<StudentTask> studentTasks = new LinkedHashSet<>();

}
