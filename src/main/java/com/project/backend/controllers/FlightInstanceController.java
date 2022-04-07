package com.project.backend.controllers;


import com.project.backend.Util.UtilHelper;
import com.project.backend.models.FlightInstance;
import com.project.backend.services.FlightInstanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/fi")
public class FlightInstanceController {

    @Autowired
    private FlightInstanceService fiService;

    @GetMapping("")
    public List<FlightInstance> getAll() {
        return fiService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity getFIByCode(@PathVariable Integer id){
        try {
            FlightInstance item = fiService.findById(id);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }


    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity updateFI(@RequestBody FlightInstance instance, @PathVariable Integer id){
        try{
            FlightInstance item = fiService.update(instance,id);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }

    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity deleteFI(@PathVariable Integer id){
        try{
            FlightInstance item = fiService.delete(id);
            return ResponseEntity.ok(item);
        }catch(Exception e){
            return UtilHelper.exceptionMapper(e);
        }

    }

}
