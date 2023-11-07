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

    @Column(name = "feature_name", columnDefinition = "NVARCHAR(200)")
    private String name;

    @Column(columnDefinition = "BIT")
    private boolean status;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(columnDefinition = "DATE")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(columnDefinition = "DATE")
    private Date endDate;


    //--------------------Relationship--------------------
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "feature", cascade = CascadeType.REMOVE)
    private List<Task> taskList;
}