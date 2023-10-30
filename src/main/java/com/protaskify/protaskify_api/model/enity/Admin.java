package com.protaskify.protaskify_api.model.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admin")
public class Admin implements UserDetails {
    //--------------------Attribute--------------------
    @Id
    @JsonProperty("RollNumber")
    @Column(name = "admin_id", columnDefinition = "CHAR(10)")
    private String id;

    @Column(name = "admin_name", columnDefinition = "NVARCHAR(50)")
    @JsonProperty("FullName")
    private String name;

    @Column(columnDefinition = "VARCHAR(50)")
    @JsonProperty("MemberCode")
    private String email;

    @Column(columnDefinition = "VARCHAR(100)")
    private String picture;

    //--------------------Authentication--------------------
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ADMIN"));
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
