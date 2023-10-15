package com.protaskify.protaskify_api.model.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "process")
public class Process {
    //--------------------Attribute--------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //--------------------Relationship--------------------
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;
}