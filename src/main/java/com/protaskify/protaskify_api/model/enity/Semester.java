package com.protaskify.protaskify_api.model.enity;

import lombok.*;
import jakarta.persistence.*;

import java.util.Date;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "semesters")
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "start_Date")
    private Date startDate;

    @Column(name = "end_Date")
    private Date endDate;

    @Column(name = "status")
    private boolean status;

}