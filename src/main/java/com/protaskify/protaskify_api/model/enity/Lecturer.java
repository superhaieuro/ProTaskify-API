package com.protaskify.protaskify_api.model.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
@Table(name = "lecturer")
public class Lecturer implements UserDetails {
    @Id
    @JsonProperty("RollNumber")
    @Column(name = "lecturer_id", columnDefinition = "CHAR(10)")
    private String id;

    @Column(name = "lecturer_name", columnDefinition = "NVARCHAR(50)")
    @JsonProperty("FullName")
    private String name;

    @Column(columnDefinition = "VARCHAR(50)")
    @JsonProperty("MemberCode")
    private String email;

    private String picture;

    private Boolean status;

    @OneToMany(mappedBy = "lecturer")
    private List<Classes> classesList;

    public void setStatus(Boolean status) {
        this.status = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("LECTURER"));
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
