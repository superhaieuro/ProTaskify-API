package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "messages")
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", columnDefinition = "INT")
    private int id;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String content;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
//    @Column(columnDefinition = "CHAR(10)")
    private Lecturer lecturerId;

    @ManyToOne
    @JoinColumn(name = "student_id")
//    @Column(columnDefinition = "CHAR(10)")
    private Student studentId;

    private Date date;

    private String fromId;
    private Boolean status;
}
