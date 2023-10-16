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

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "student")
public class Student implements UserDetails {
    @Id
    @Column(name = "student_id", nullable = false, length = 50)
    private String studentId;

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

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "github", length = 50)
    private String github;

    @Column(name = "skills", length = 50)
    private String skills;

    @Column(name = "about", length = 100)
    private String about;

    @Lob
    @Column(name = "picture")
    private String picture;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "is_leader")
    private Boolean isLeader;

    @OneToMany(mappedBy = "studentReviewer")
    private Set<Star> stars = new LinkedHashSet<>();

    @OneToMany(mappedBy = "student")
    private Set<StudentTask> studentTasks = new LinkedHashSet<>();

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
