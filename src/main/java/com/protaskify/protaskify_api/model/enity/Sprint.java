package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sprint")
public class Sprint {
    @Id
    @Column(name = "sprint_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sprint_name",columnDefinition = "VARCHAR(25)")
    private String name;
    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class classId;
    @Column(columnDefinition = "DATE")
    private Date start_date;
    @Column(columnDefinition = "DATE")
    private Date end_date;
    @Column(columnDefinition = "VARCHAR(25)")
    private String feedback;
    @Column(columnDefinition = "BIT")
    private boolean status;

}