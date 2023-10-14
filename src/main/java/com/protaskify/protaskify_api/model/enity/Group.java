package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "groups") //Trong DB thì bảng tên group(s) nhưng trong Backend thì Object tên là Group
public class Group {
    //--------------------Attribute--------------------
    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_name", columnDefinition = "NVARCHAR(50)")
    private String name;

    @Column(columnDefinition = "FLOAT")
    private Double score;


    //--------------------Relationship--------------------
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Classes classes;

    @OneToMany(mappedBy = "group")
    private List<Student> studentList;
}