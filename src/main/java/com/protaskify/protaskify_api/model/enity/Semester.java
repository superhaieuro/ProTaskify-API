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
@Table(name = "semesters")
public class Semester {
    //--------------------Attribute--------------------
    @Id
    @Column(name = "semester_id", columnDefinition = "CHAR(10)")
    private String id;

    @Column(name = "semester_name", columnDefinition = "VARCHAR(10)")
    private String name;

    @Column(columnDefinition = "DATE")
    private Date startDate;

    @Column(columnDefinition = "DATE")
    private Date endDate;

    private boolean status;


    //--------------------Relationship--------------------
//    @OneToMany(mappedBy = "semester")
//    private List<Classes> classesList;
}
