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
@Table(name = "groups")
public class Groups {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", columnDefinition = "VARCHAR(10)")
    private String id;
    private String groupName;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Classes classes;

    @OneToMany(mappedBy = "groups")
    private List<Student> studentList;
}
