package com.protaskify.protaskify_api.model.enity;

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
    private Date start_date;

    @Column(columnDefinition = "DATE")
    private Date end_date;

    @Column(columnDefinition = "VARCHAR(25)")
    private String feedback;

    @Column(columnDefinition = "BIT")
    private boolean status;


    //--------------------Relationship--------------------
    @ManyToOne
    @JoinColumn(name = "class_id")
    private Classes classes;
}