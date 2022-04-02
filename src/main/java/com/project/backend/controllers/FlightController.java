package com.project.backend.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project.backend.models.Flight;
import com.project.backend.services.FlightInstanceService;
import com.project.backend.services.FlightService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;

    //tmp
    @Autowired
    private FlightInstanceService flightInstanceService;

    @PostMapping("add")
    public String addFlight(@RequestBody ObjectNode objectNode){
        String routeCode = objectNode.get("routeCode").asText();
        String planeCode = objectNode.get("planeCode").asText();
        String departureTime = objectNode.get("departureTime").asText();
        Double fare = objectNode.get("fare").asDouble();
        String backDepartTime = flightService.backFlightDepartTime(routeCode,departureTime);
        flightService.addFlight(routeCode,planeCode,departureTime,fare);
        flightService.addFlight(routeCode,planeCode,backDepartTime,fare);
        return backDepartTime;
    }
    @PutMapping("update/{id}")
    public boolean updateFlight(@RequestBody Flight flight, @PathVariable Integer flightId){
        return flightService.updateFlight(flight.getFlightId(),flight.getRouteCode(),
                flight.getICAOCode(),flight.getDepartureTime(),flight.getFare());
    }
    @DeleteMapping("delete/{id}")
    public boolean deleteFlight(@PathVariable Integer flightId){
        return flightService.deleteFlight(flightId);
    }


}
