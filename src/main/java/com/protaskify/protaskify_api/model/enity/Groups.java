package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.*;
import lombok.Data;

@Data
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
}
