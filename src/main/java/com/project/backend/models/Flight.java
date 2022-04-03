package com.project.backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer flightId;

    @Column(name = "route_code")
    private String routeCode;

    @Column(name = "ICAO_code")
    private String ICAOCode;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "fare")
    private Double fare;

}
