package com.project.backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Airports")
public class Airport {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "country_code")
    private String country_code;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longtitude")
    private Double longtitude;

    @Column(name = "name")
    private String name;

}
