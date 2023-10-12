package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @Column(name = "message_id", nullable = false, length = 50)
    private String messageId;

    @Column(name = "type", length = 50)
    private String type;

    @Column(name = "request_note", length = 50)
    private String requestNote;

    @Lob
    @Column(name = "create_note")
    private String createNote;

    @Column(name = "respone_note", length = 50)
    private String responeNote;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "change_status_time")
    private Instant changeStatusTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

}
