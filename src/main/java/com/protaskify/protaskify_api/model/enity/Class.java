package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "class")
public class Class {
    @Id
    @Column(name = "class_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_name", columnDefinition = "VARCHAR(50)")
    private String name;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;
}