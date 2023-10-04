package com.protaskify.protaskify_api.model.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
    @Column(name = "student_id", columnDefinition = "CHAR(10)")
    private String id;

    @Column(name = "student_name", columnDefinition = "NVARCHAR(50)")
    @JsonProperty("FullName")
    private String name;

    @Column(columnDefinition = "VARCHAR(50)")
    @JsonProperty("MemberCode")
    private String email;

    private String picture;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Classes classes;

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
