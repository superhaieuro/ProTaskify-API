package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "feature")
@Entity
public class Feature {
    @Id
    @Column(name = "feature_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "feature_name", columnDefinition = "VARCHAR(50)")
    private String name;
    @Column(columnDefinition = "BIT")
    private boolean status;
    @Column(columnDefinition = "VARCHAR(MAX)")
    private String description;
}