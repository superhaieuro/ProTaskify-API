package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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

}
