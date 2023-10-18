package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "semester")
public class Semester {
    @Id
    @Column(name = "semester_id", nullable = false, length = 50)
    private String semesterId;

    @Column(name = "semester_name", length = 50)
    private String semesterName;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "semester")
    private Set<Class> classFields = new LinkedHashSet<>();

}
