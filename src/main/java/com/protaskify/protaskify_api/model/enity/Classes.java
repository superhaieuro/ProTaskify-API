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
@Table(name = "class")
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id", columnDefinition = "INT")
    private int id;

    @Column(name = "class_name", columnDefinition = "VARCHAR(50)")
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @OneToMany(mappedBy = "classes")
    private List<Student> studentList;
}
