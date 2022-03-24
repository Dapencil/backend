package com.project.backend.controllers;

import com.project.backend.models.Plane;
import com.project.backend.repositories.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlaneController {

    @Autowired
    private PlaneRepository repository;


    public List<Plane> getPlanes(){
        return repository.findAll();
    }

}
