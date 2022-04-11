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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
@Table(name = "airport")
public class Airport {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longtitude")
    private Double longitude;

    @Column(name = "name")
    private String name;

    @Column(name = "time_zone")
    private String timeZone;
}
