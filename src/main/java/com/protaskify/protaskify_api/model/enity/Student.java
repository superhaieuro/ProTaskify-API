package com.protaskify.protaskify_api.model.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
    @Column(name = "student_id", columnDefinition = "CHAR(10)")
    @JsonProperty("RollNumber")
    private String id;

    @Column(columnDefinition = "NVARCHAR(50)")
    @JsonProperty("FullName")
    private String name;

    @JsonProperty("MemberCode")
    private String email;

    @Column(columnDefinition = "VARCHAR(100)")
    private String github;

    @Column(columnDefinition = "VARCHAR(100)")
    private String facebook;

    @Column(columnDefinition = "VARCHAR(20)")
    private String skills;

    @Column(columnDefinition = "FLOAT")
    private Double score;

    @Column(columnDefinition = "BIT")
    private boolean status = true;

    @Column(columnDefinition = "BIT")
    private boolean isLeader;

    @Column(columnDefinition = "VARCHAR(100)")
    private String picture;


    //--------------------Relationship--------------------
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "class_id")
    private Classes classes;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToMany
    @JsonIgnore
    private List<Task> taskList;

    public void setEmail(String email) {
        this.email = email + "@fpt.edu.vn";
    }


    //--------------------Authentication--------------------
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