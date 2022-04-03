package com.project.backend.models;


import com.project.backend.keys.UserPK;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@IdClass(UserPK.class)
public class User {

    @Id
    @Column(name = "id")
    private Integer id;

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "f_name")
    private String firstName;

    @Column(name = "l_name")
    private String lastName;

    @Column(name = "regis_date")
    private LocalDate regisDate;

    @Column(name = "date_of_brith")
    private LocalDate dob;

    @Column(name = "total_mile")
    private Integer totalMile;

    @Column(name = "role")
    private String role;

}
