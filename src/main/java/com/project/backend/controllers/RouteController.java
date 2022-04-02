package com.project.backend.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project.backend.models.Airport;
import com.project.backend.models.Route;
import com.project.backend.services.AirportService;
import com.project.backend.services.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private AirportService airportService;

    @PostMapping("add")
    public String addRoute(@RequestBody ObjectNode objectNode){
        String tempFrom = objectNode.get("from").asText();
        String tempTo = objectNode.get("to").asText();
        String routeCode = objectNode.get("code").asText();
        Airport fromAirport = airportService.findAirportByCode(tempFrom);
        Airport toAirport = airportService.findAirportByCode(tempTo);
        try{
            Route route = routeService.addRoute(routeCode,fromAirport,toAirport);
            return String.format("from : %s to : %s distance %d taken time %d",route.getFromAirport(),route.getToAirport(),route.getDistance(),route.getTakenTime());
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @GetMapping("getAll")
    public List<Route> getRoute() {
        return routeService.getAllRoute();
    }
    @GetMapping("get/{routeCode}")
    public Route getRouteByCode(@PathVariable String routeCode){
        return routeService.findRouteByRouteCode(routeCode);
    }
    @PutMapping("update/{routeCode}")
    public boolean updateRoute(@RequestBody Route route, @PathVariable String routeCode){
        return routeService.updateRoute(route.getCode(),route.getFromAirport(),route.getToAirport());
    }
    @DeleteMapping("delete/{code}")
    public boolean deleteRoute(@PathVariable String code){
        return routeService.deleteRoute(code);
    }

}
