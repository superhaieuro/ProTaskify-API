package com.protaskify.protaskify_api.model.enity;

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
@Table(name = "semester")
public class Semester {
    //--------------------Attribute--------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "semester_id")
    private Long id;

    @Column(name = "semester_name", columnDefinition = "VARCHAR(11)")
    private String name;

    @Column(columnDefinition = "DATE")
    private Date startDate;

    @Column(columnDefinition = "DATE")
    private Date endDate;

    private boolean status;


    //--------------------Relationship--------------------
    @OneToMany(mappedBy = "semester")
    private List<Classes> classesList;
}
