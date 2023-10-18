package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @Column(name = "admin_id", nullable = false, length = 50)
    private String adminId;

    @Column(name = "admin_name", length = 50)
    private String adminName;

    @Column(name = "email", length = 50)
    private String email;

}
