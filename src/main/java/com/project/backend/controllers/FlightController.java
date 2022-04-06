package com.project.backend.controllers;

import com.project.backend.Util.UtilHelper;
import com.project.backend.models.Flight;
import com.project.backend.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("")
    public List<Flight> getAll(){
        return flightService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity getById(@PathVariable Integer id){
        try{
            Flight item = flightService.findById(id);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity addFlight(@RequestBody Flight flight){
        try{
            List<Flight> item = flightService.add(flight);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity updateFlight(@RequestBody Flight flight ,@PathVariable Integer id){
        try{
            List<Flight> item = flightService.update(flight,id);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity deleteFlight(@PathVariable Integer id){
        try{
            List<Flight> item = flightService.delete(id);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

}
