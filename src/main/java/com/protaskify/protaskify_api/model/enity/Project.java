package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
public class Project {
    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "project_name",columnDefinition = "VARCHAR(50)")
    private String name;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group groupId;
    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturerId;
    @Column(columnDefinition = "BIT")
    private boolean status;
    @Column(columnDefinition = "VARCHAR(100)")
    private String problems;
    @Column(columnDefinition = "VARCHAR(100)")
    private String context;
    @Column(columnDefinition = "VARCHAR(250)")
    private String functional_requirements;
    @Column(columnDefinition = "VARCHAR(250)")
    private String non_functional_requirements;

}