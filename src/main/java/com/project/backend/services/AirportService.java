package com.project.backend.services;

import com.project.backend.models.Airport;
import com.project.backend.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService {

    @Autowired
    private AirportRepository repository;

    public Airport findAirportByCode(String code){
        return repository.findById(code).get();
    }
}
