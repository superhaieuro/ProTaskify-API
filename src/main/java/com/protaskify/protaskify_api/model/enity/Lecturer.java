package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "lecturer")
public class Lecturer implements UserDetails {
    @Id
    @Column(name = "lecturer_id", nullable = false, length = 50)
    private String lecturerId;

    @Nationalized
    @Column(name = "lecturer_name", length = 50)
    private String lecturerName;

    @Column(name = "email", length = 50)
    private String email;

    @Lob
    @Column(name = "picture", nullable = false)
    private String picture;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "lecturer")
    private Set<Class> classFields = new LinkedHashSet<>();

    @OneToMany(mappedBy = "lecturer")
    private Set<Message> messages = new LinkedHashSet<>();

    @OneToMany(mappedBy = "lecturer")
    private Set<Project> projects = new LinkedHashSet<>();

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
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
