package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Date;
import java.util.Set;

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
    private Date create_date;

    @Column(columnDefinition = "DATE")
    private Date finish_date;


    //--------------------Relationship--------------------
    @JsonIgnore
    @ManyToMany
    private Set<Student> students;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "feature_id")
    private Feature featureId;
}