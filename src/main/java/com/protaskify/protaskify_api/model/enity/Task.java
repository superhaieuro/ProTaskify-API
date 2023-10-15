package com.protaskify.protaskify_api.model.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public class Task {
    //--------------------Attribute--------------------
    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_name", columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(columnDefinition = "BIT")
    private boolean status;

    @Column(columnDefinition = "VARCHAR(MAX)")
    private String feedback;

    @Column(columnDefinition = "VARCHAR(25)")
    private String priority;

    @Column(columnDefinition = "VARCHAR(MAX)")
    private String description;

    @Column(columnDefinition = "DATE")
    private Date createDate;

    @Column(columnDefinition = "DATE")
    private Date finishDate;


    //--------------------Relationship--------------------
    @ManyToMany(mappedBy = "taskList")
   @JsonIgnore
    private List<Student> studentList;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "feature_id")
    private Feature feature;
}