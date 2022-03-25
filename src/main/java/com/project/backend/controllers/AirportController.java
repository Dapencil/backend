package com.project.backend.controllers;


import com.project.backend.models.Airport;
import com.project.backend.repositories.AirportRepository;
import com.project.backend.services.AirportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping("getAll")
    public List<Airport> getAirports() {
        return airportService.getAirport();
    }
    @GetMapping("getByCode")
    public Airport getAirportByCode(@RequestParam(name = "code") String code){
        return airportService.findAirportByCode(code);
    }
    @PostMapping("add")
    public int addAirport(@RequestBody Airport airport){
        return airportService.addAirport(airport.getCode(),airport.getCountry_code(),
                airport.getLatitude(),airport.getLongtitude(),airport.getName());
    }
}
