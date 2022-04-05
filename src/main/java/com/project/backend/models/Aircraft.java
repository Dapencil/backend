package com.project.backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
@Table(name = "aircraft")
public class Aircraft {

    @Id
    @Column(name = "reg_number")
    private String regNum;

    @Column(name = "ICAO_code")
    private String ICAOCode;

    @Column(name = "MSN")
    private String MSN;

    @Column(name = "first_flight")
    private LocalDate firstFlight;

    @Column(name = "deliver_date")
    private LocalDate deliverDate;

}
