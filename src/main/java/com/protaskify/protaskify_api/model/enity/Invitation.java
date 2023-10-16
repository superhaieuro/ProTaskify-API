package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "accepted")
    private Boolean accepted;

    @Column(name = "createdAt", nullable = false)
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "receiverID", nullable = false)
    private Student receiverID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupID")
    private Group groupID;

}
