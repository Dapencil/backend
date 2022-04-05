package com.project.backend.controllers;


import com.project.backend.Util.UtilHelper;
import com.project.backend.models.Airport;
import com.project.backend.services.AirportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping("")
    public List<Airport> getAll() {
        return airportService.getAll();
    }

    @GetMapping("/{code}")
    @ResponseBody
    public ResponseEntity getAirportByCode(@PathVariable String code){
        try {
            Airport item = airportService.findByCode(code);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity addAirport(@RequestBody Airport airport){
        try {
            Airport item = airportService.add(airport);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }

    }

    @PutMapping("/{code}")
    @ResponseBody
    public ResponseEntity updateAirport(@RequestBody Airport airport, @PathVariable String code){
        try{
            Airport item = airportService.update(airport,code);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }

    }

    @DeleteMapping("/{code}")
    @ResponseBody
    public ResponseEntity deleteAirport(@PathVariable String code){
        try{
            Airport item = airportService.delete(code);
            return ResponseEntity.ok(item);
        }catch(Exception e){
            return UtilHelper.exceptionMapper(e);
        }

    }
}
