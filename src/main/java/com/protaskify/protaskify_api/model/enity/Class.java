package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "class")
public class Class {
    @Id
    @Column(name = "class_id", nullable = false, length = 50)
    private String classId;

    @Column(name = "class_name", length = 50)
    private String className;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @OneToMany(mappedBy = "classField")
    private Set<Group> groups = new LinkedHashSet<>();

    @OneToMany(mappedBy = "classField")
    private Set<Sprint> sprints = new LinkedHashSet<>();

    @OneToMany(mappedBy = "classField")
    private Set<Student> students = new LinkedHashSet<>();

}
