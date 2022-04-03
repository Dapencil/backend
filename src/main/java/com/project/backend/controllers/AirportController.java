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
    @GetMapping("get/{code}")
    public Airport getAirportByCode(@PathVariable String code){
        return airportService.findAirportByCode(code);
    }
    @PostMapping("add")
    public boolean addAirport(@RequestBody Airport airport){
        return airportService.addAirport(airport.getCode(),airport.getCountry_code(),
                airport.getLatitude(),airport.getLongtitude(),airport.getName(),airport.getTime_zone());
    }
    @PutMapping("update/{code}")
    public boolean updateAirport(@RequestBody Airport airport, @PathVariable String code){
        return airportService.updateAirport(airport.getCode(),airport.getCountry_code(),
                airport.getLatitude(),airport.getLongtitude(),airport.getName(),airport.getTime_zone());
    }
    @DeleteMapping("delete/{code}")
    public boolean deleteAirport(@PathVariable String code){
        return airportService.deleteAirport(code);
    }
}
