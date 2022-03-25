package com.project.backend.controllers;

import com.project.backend.models.Airport;
import com.project.backend.models.Plane;
import com.project.backend.repositories.PlaneRepository;
import com.project.backend.services.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/plane")
public class PlaneController {

    @Autowired
    private PlaneService planeService;

    @GetMapping("getAll")
    public List<Plane> getPlanes(){
        return planeService.getPlane();
    }

    @PostMapping("add")
    public int addPlane(@RequestBody Plane plane){
        return planeService.addPlane(plane.getCode(),
                plane.getModel(),plane.getCapacity());
    }
}
