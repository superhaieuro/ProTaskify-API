package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "group_name", columnDefinition = "VARCHAR(25)")
    private String name;
    @Column(columnDefinition = "FLOAT")
    private Double score;
    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class classId;
}