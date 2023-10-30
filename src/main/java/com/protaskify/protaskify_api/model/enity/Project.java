package com.protaskify.protaskify_api.model.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("Subject name")
    private String name;

    @Column(columnDefinition = "BIT")
    private boolean status;

    @JsonProperty("Problems")
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String problems;

    @JsonProperty("Context")
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String context;

    @JsonProperty("Actors")
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String actors;

    @JsonProperty("Functional requirements")
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String functionalRequirements;

    @JsonProperty("Non-Functional requirements")
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String nonFunctionalRequirements;


    //--------------------Relationship--------------------
    @OneToMany(mappedBy = "project")
    @JsonIgnore
    private List<Group> groupList;

}