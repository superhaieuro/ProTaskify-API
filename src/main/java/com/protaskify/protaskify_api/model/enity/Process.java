package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "process")
public class Process {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project projectId;
    @ManyToOne
    @JoinColumn(name = "sprint_id")
    private Sprint sprintId;
    @ManyToOne
    @JoinColumn(name = "feature_id")
    private Feature featureId;
}