package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "\"group\"")
public class Group {
    @Id
    @Column(name = "group_id", nullable = false, length = 50)
    private String groupId;

    @Column(name = "group_name", length = 50)
    private String groupName;

    @Column(name = "score")
    private Double score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "class_id", nullable = false)
    private Class classField;

    @OneToMany(mappedBy = "group")
    private Set<Message> messages = new LinkedHashSet<>();

    @OneToMany(mappedBy = "group")
    private Set<Student> students = new LinkedHashSet<>();

}
