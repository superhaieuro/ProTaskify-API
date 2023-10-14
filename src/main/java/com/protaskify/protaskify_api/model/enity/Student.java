package com.protaskify.protaskify_api.model.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student implements UserDetails {
    //--------------------Attribute--------------------
    @Id
    @JsonProperty("RollNumber")
    @Column(name = "student_id", columnDefinition = "CHAR(10)")
    private String id;

    @Column(name = "student_name", columnDefinition = "NVARCHAR(50)")
    @JsonProperty("FullName")
    private String name;

    @Column(columnDefinition = "VARCHAR(50)")
    @JsonProperty("MemberCode")
    private String email;

    @Column(columnDefinition = "VARCHAR(150)")
    private String github;

    @Column(columnDefinition = "VARCHAR(150)")
    private String facebook;

    @Column(columnDefinition = "VARCHAR(50)")
    private String skills;

    @Column(columnDefinition = "FLOAT")
    private Double score;

    @Column(columnDefinition = "BIT")
    private boolean status = true;

    @Column(columnDefinition = "BIT")
    private boolean isLeader;

    private String picture;


    //--------------------Relationship--------------------
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "class_id")
    private Classes classes;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public void setEmail(String email) {
        this.email = email + "@fpt.edu.vn";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("STUDENT"));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
