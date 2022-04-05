package com.project.backend.controllers;

import com.project.backend.models.Aircraft;
import com.project.backend.services.AircraftService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/aircraft")
public class AircraftController {

    @Autowired
    private AircraftService aircraftService;

    @GetMapping("")
    public List<Aircraft> getAll(){
        return aircraftService.getAll();
    }

    @GetMapping("/{regNum}")
    @ResponseBody
    public ResponseEntity getAircraftByCode(@PathVariable String regNum){
        try{
            Aircraft item = aircraftService.findByRegNum(regNum);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity addAircraft(@RequestBody Aircraft aircraft){
        try{
            Aircraft item = aircraftService.add(aircraft);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{regNum}")
    @ResponseBody
    public ResponseEntity update(@RequestBody Aircraft aircraft, @PathVariable String regNum){
        try{
            Aircraft item = aircraftService.update(aircraft,regNum);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{regNum}")
    public ResponseEntity delete(@PathVariable String regNum){
        try {
            Aircraft item = aircraftService.delete(regNum);
            return ResponseEntity.ok(item);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
