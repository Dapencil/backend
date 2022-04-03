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
@Table(name = "model")
public class Model {

    @Id
    @Column(name = "ICAO_code")
    private String ICAOCode;

    @Column(name = "name")
    private String name;

    @Column(name = "seats")
    private Integer seats;

    @Column(name = "agent")
    private String agent;

    @Column(name = "speed")
    private Double speed;
}
