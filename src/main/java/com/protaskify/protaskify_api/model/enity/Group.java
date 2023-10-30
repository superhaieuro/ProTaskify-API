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

    @Column(columnDefinition = "BIT")
    private boolean status;


    //--------------------Relationship--------------------
    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "class_id")
    private Classes classes;

    @OneToMany(mappedBy = "group")
    private List<Feature> featureList;

    @OneToMany(mappedBy = "group")
    private List<Student> studentList;
}