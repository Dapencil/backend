package com.project.backend.controllers;

import com.project.backend.Util.UtilHelper;
import com.project.backend.models.Airport;
import com.project.backend.models.Route;
import com.project.backend.services.AirportService;
import com.project.backend.services.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("")
    public List<Route> getAll() {
        return routeService.getAll();
    }

    @GetMapping("/{code}")
    @ResponseBody
    public ResponseEntity getRouteByCode(@PathVariable String code){
        try {
            Route item = routeService.findByCode(code);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity addRoute(@RequestBody Route route){
        try{
            Airport from = airportService.findByCode(route.getFromAirport());
            Airport to = airportService.findByCode(route.getToAirport());
            Route item = routeService.addRoute(route,from,to);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @PutMapping("/{code}")
    @ResponseBody
    public ResponseEntity updateRoute(@RequestBody Route route, @PathVariable String routeCode){
        try{
            Airport from = airportService.findByCode(route.getFromAirport());
            Airport to = airportService.findByCode(route.getToAirport());
            Route item = routeService.updateRoute(route,from,to,routeCode);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @DeleteMapping("/{code}")
    @ResponseBody
    public ResponseEntity deleteRoute(@PathVariable String code){
        try{
            Route item = routeService.deleteRoute(code);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

}
