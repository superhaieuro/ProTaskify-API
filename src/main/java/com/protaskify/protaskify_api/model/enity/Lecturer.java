package com.protaskify.protaskify_api.model.enity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lecturersadmin")
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "lecturer_name")
    private String name;

    @Column(name = "member_code")
    private String email;

    @Column(name = "status")
    private boolean status;

//    public boolean getStatus() {
//        return true;
//    }
//    private String picture;
}
