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
@Table(name = "flight_data")
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

}
