package com.project.backend.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flightInstances")
public class FlightInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instance_id")
    private Integer instanceId;

    @Column(name = "flight_id")
    private Integer flightId;

    @Column(name = "flight_date")
    private LocalDateTime flightDate;

}
