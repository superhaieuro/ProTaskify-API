package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "sprint")
public class Sprint {
    @Id
    @Column(name = "sprint_id", nullable = false, length = 50)
    private String sprintId;

    @Column(name = "sprint_name", length = 50)
    private String sprintName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Class classField;

    @Column(name = "feedback", length = 100)
    private String feedback;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToMany(mappedBy = "sprint")
    private Set<Process> processes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "sprint")
    private Set<Star> stars = new LinkedHashSet<>();

}
