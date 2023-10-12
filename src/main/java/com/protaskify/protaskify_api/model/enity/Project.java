package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "project")
public class Project {
    @Id
    @Column(name = "project_id", nullable = false, length = 50)
    private String projectId;

    @Lob
    @Column(name = "project_name")
    private String projectName;

    @Column(name = "status")
    private Boolean status;

    @Lob
    @Column(name = "problems")
    private String problems;

    @Lob
    @Column(name = "context")
    private String context;

    @Lob
    @Column(name = "functional_requirements")
    private String functionalRequirements;

    @Lob
    @Column(name = "non_functional_requirements")
    private String nonFunctionalRequirements;

}
