package com.protaskify.protaskify_api.model.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student implements UserDetails {
    @Id
    @JsonProperty("RollNumber")
    private String id;
    @JsonProperty("FullName")
    private String name;
    @JsonProperty("MemberCode")
    private String email;
    private String picture;

    @Nationalized
    @Column(name = "student_name", length = 50)
    private String studentName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Class classField;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "score")
    private Double score;

    @Column(name = "link_facebook", length = 100)
    private String linkFacebook;

    @Column(name = "github", length = 50)
    private String github;

    @Column(name = "skills", length = 50)
    private String skills;

    @Column(name = "about", length = 100)
    private String about;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "is_leader")
    private Boolean isLeader;

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
