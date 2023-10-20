package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
public class Project {
    //--------------------Attribute--------------------
    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_name", columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(columnDefinition = "BIT")
    private boolean status;

    @Column(columnDefinition = "VARCHAR(MAX)")
    private String problems;

    @Column(columnDefinition = "VARCHAR(MAX)")
    private String context;

    @Column(columnDefinition = "VARCHAR(MAX)")
    private String functionalRequirements;

    @Column(columnDefinition = "VARCHAR(MAX)")
    private String nonFunctionalRequirements;


    //--------------------Relationship--------------------
    @OneToMany(mappedBy = "project")
    private List<Group> groupList;

}