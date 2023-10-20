package com.protaskify.protaskify_api.model.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sprint")
public class Sprint {
    //--------------------Attribute--------------------
    @Id
    @Column(name = "sprint_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sprint_name", columnDefinition = "VARCHAR(25)")
    private String name;

    @Column(columnDefinition = "DATE")
    private Date startDate;

    @Column(columnDefinition = "DATE")
    private Date endDate;

    @Column(columnDefinition = "VARCHAR(25)")
    private String feedback;

    @Column(columnDefinition = "BIT")
    private boolean status;


    //--------------------Relationship--------------------
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "class_id")
    private Classes classes;
}