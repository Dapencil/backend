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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
@Table(name = "route")
public class Route {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "from_airport")
    private String fromAirport;

    @Column(name = "to_airport")
    private String toAirport;

    @Column(name = "distance")
    private Integer distance;

    @Column(name = "taken_time")
    private Integer takenTime;

}
