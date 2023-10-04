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

    @Column(columnDefinition = "CHAR(10)")
    private String fromId;

    @Column(columnDefinition = "CHAR(10)")
    private String toId;

    private Date createDate;
    private Boolean status;
}
