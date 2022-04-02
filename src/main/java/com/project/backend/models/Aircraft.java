package com.project.backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "aircraft")
public class Aircraft {

    @Id
    @Column(name = "reg_number")
    private String regNum;

    @Column(name = "ICAO_code")
    private String ICAOCode;

    @Column(name = "MSN")
    private String msn;

    @Column(name = "first_flight")
    private LocalDate fFlight;

    @Column(name = "deliver_flight")
    private LocalDate dFlight;

}
