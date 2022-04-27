package com.project.backend.controllers;

import com.project.backend.models.ResponseModel.AvailableFlight;
import com.project.backend.services.ResService.AvailableFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/availableFlight")
public class AvailableFlightController {

    @Autowired
    private AvailableFlightService service;

    @GetMapping("")
    public List<AvailableFlight> getAvailableFlightFrom(@RequestParam String from, @RequestParam String to, @RequestParam LocalDate date){
        return service.getAvailable(from, to, date);
    }
}
