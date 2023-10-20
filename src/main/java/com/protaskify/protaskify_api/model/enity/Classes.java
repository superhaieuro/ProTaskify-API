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
@Table(name = "class") //Trong DB thì bảng tên class nhưng trong Backend thì Object tên là Class(es)
public class Classes {
    //--------------------Attribute--------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long id;

    @Column(name = "class_name", columnDefinition = "VARCHAR(50)")
    private String name;


    //--------------------Relationship--------------------
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @OneToMany(mappedBy = "classes")
    private List<Student> studentList;

    @OneToMany(mappedBy = "classes")
    private List<Sprint> sprintList;

    @OneToMany(mappedBy = "classes")
    private List<Group> groupList;
}