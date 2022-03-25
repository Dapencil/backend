package com.project.backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Integer flightId;

    @Column(name = "route_code")
    private String routeCode;

    @Column(name = "plane_code")
    private String planeCode;

    @Column(name = "departure_time")
    private String departureTime;

    @Column(name = "fare")
    private Double fare;

}
