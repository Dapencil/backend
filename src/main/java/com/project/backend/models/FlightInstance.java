package com.project.backend.models;


import com.fasterxml.jackson.annotation.JsonFormat;
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
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
@AllArgsConstructor
@Table(name = "flightinstance")
public class FlightInstance {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="FISequenceGenerator")
    @SequenceGenerator(allocationSize=1, name="FISequenceGenerator", sequenceName = "fi_sequence")
    @Column(name = "instance_id")
    private Integer instanceId;

    @Column(name = "flight_id")
    private Integer flightId;

    @Column(name = "flight_date")
    private LocalDateTime flightDate;

    @Column(name = "aircraft_reg_num")
    private String aircraftRegNum;

}
