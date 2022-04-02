package com.project.backend.controllers;

import com.project.backend.models.Aircraft;
import com.project.backend.services.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/aircraft")
public class AircraftController {

    @Autowired
    private AircraftService aircraftService;
    @GetMapping("getAll")
    public List<Aircraft> getAircraft(){
        return aircraftService.getAircraft();
    }
    @GetMapping("get/{code}")
    public Aircraft getAircraftByCode(@PathVariable String code){
        return aircraftService.findAircraftByCode(code);
    }
    @PostMapping("add")
    public boolean addAircraft(@RequestBody Aircraft aircraft){
        return aircraftService.addAircraft(aircraft.getRegNum(),aircraft.getICAOCode(),
                aircraft.getMsn(),aircraft.getFFlight(),aircraft.getDFlight());
    }
    @PutMapping("update/{code}")
    public boolean updateAircraft(@RequestBody Aircraft aircraft, @PathVariable String code){
        return aircraftService.updateAircraft(aircraft.getRegNum(),aircraft.getICAOCode(),
                aircraft.getMsn(),aircraft.getFFlight(),aircraft.getDFlight());
    }
    @DeleteMapping("delete/{code}")
    public boolean deleteAircraft(@PathVariable String code){
        return aircraftService.deletePlane(code);
    }
}
