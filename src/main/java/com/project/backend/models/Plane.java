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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "planes")
public class Plane {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "model")
    private String model;

    @Column(name = "capacity")
    private Integer capacity;

}
