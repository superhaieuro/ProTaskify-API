package com.protaskify.protaskify_api.model.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feature")
public class Feature {
    //--------------------Attribute--------------------
    @Id
    @Column(name = "feature_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "feature_name", columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(columnDefinition = "BIT")
    private boolean status;

    @Column(columnDefinition = "VARCHAR(MAX)")
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(columnDefinition = "DATE")
    private Date start_date;

    @Temporal(TemporalType.DATE)
    @Column(columnDefinition = "DATE")
    private Date end_date;


    //--------------------Relationship--------------------
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "feature")
    private List<Task> taskList;
}