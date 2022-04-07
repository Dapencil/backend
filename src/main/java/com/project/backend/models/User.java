package com.project.backend.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="userSequenceGenerator")
    @SequenceGenerator(allocationSize=1, name="userSequenceGenerator", sequenceName = "user_sequence")
    @Column(name = "id")
    private Integer id;

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
    private LocalDate DOB;

    @Column(name = "total_mile")
    private Integer totalMile;

    @Column(name = "role")
    private String role;

}
