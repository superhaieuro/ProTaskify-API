package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "feature")
public class Feature {
    @Id
    @Column(name = "feature_id", nullable = false, length = 50)
    private String featureId;

    @Column(name = "feature_name", length = 50)
    private String featureName;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "description", length = 100)
    private String description;

    @OneToMany(mappedBy = "feature")
    private Set<Process> processes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "feature")
    private Set<Task> tasks = new LinkedHashSet<>();

}
