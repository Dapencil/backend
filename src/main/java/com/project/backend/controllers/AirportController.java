package com.project.backend.controllers;


import com.project.backend.models.Airport;
import com.project.backend.repositories.AirportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/airport")
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;

    @GetMapping("getAll")
    public List<Airport> getAirports() {
        return this.airportRepository.findAll();
    }
}
