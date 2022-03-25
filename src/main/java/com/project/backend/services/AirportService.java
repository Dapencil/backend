package com.project.backend.services;

import com.project.backend.models.Airport;
import com.project.backend.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AirportService {

    @Autowired
    private AirportRepository repository;

    public Airport findAirportByCode(String code) {
        return repository.findById(code).get();
    }

    public List<Airport> getAirport() {
        return repository.findAll();
    }

    public int addAirport(String code, String country, Double latitude, Double longtitude, String name) {
        Airport airport = new Airport();
        if (!checkCode(code)) { return 0; }
        airport.setCode(code);
        airport.setCountry_code(country);
        airport.setLatitude(Math.round(latitude * 1000000d) / 1000000d); //round to %.6f
        airport.setLongtitude(Math.round(longtitude * 1000000d) / 1000000d);
        airport.setName(name);
        repository.save(airport);
        return 1;
    }

    public boolean checkCode(String s){ //check A-Z 3 char
        final String regex = "[A-Z]{3}";
        final String string = s;
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

}