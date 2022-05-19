package com.project.backend.models.ResponseModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "available_flight")
public class AvailableFlight {

    @Id
    @Column(name = "instance_id")
    private Integer instanceId;

    @Column(name = "flight_id")
    private Integer flightId;

    @Column(name = "fare")
    private Double fare;

    @Column(name = "flight_date")
    private LocalDateTime flightDate;

    @Column(name = "from_airport")
    private String from;

    @Column(name = "to_airport")
    private String to;

    @Column(name = "from_timezone")
    private String fromTimezone;

    @Column(name = "to_timezone")
    private String toTimezone;

    @Column(name = "taken_time")
    private Integer takenTime;

    @Column(name = "available_seats")
    private Integer availableSeat;

}
