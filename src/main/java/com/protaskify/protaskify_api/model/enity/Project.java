package com.protaskify.protaskify_api.model.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "project_name", columnDefinition = "NVARCHAR(255)")
    private String name;

    @Column(columnDefinition = "BIT")
    private boolean status;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String problems;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String context;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String actors;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String functionalRequirements;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String nonFunctionalRequirements;


    //--------------------Relationship--------------------
    @OneToMany(mappedBy = "project")
    @JsonIgnore
    private List<Group> groupList;

}