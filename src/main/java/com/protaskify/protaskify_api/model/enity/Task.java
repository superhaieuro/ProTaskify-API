package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public class Task {
    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "task_name", columnDefinition = "VARCHAR(25)")
    private String name;
    @ManyToOne
    @JoinColumn(name = "feature_id")
    private Feature featureId;
    @Column(columnDefinition = "VARCHAR(10)")
    private String status;
    @Column(columnDefinition = "VARCHAR(25)")
    private String feedback;
    @Column(columnDefinition = "VARCHAR(25)")
    private String priority;
    @Column(columnDefinition = "VARCHAR(MAX)")
    private String description;
    @Column(columnDefinition = "DATE")
    private Date create_date;
    @Column(columnDefinition = "DATE")
    private Date finish_date;

//    @ManyToMany
//    Set<Student> student;
}