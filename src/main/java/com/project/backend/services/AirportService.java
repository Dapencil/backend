package com.project.backend.services;

import com.project.backend.models.Airport;
import com.project.backend.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public boolean addAirport(String code, String country, Double latitude, Double longtitude, String name,String timeZone) {
        Airport airport = new Airport();
        if (!checkCode(code)) { return false; }
        airport.setCode(code);
        airport.setCountryCode(country);
        airport.setLatitude(Math.round(latitude * 1000000d) / 1000000d); //round to %.6f
        airport.setLongtitude(Math.round(longtitude * 1000000d) / 1000000d);
        airport.setTimeZone(timeZone);
        airport.setName(name);
        repository.save(airport);
        return true;
    }
    public boolean checkCode(String s){ //check A-Z 3 char
        final String regex = "[A-Z]{3}";
        final String string = s;
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public boolean updateAirport(String code, String country, Double latitude, Double longtitude, String name,String timeZone){
        Optional<Airport> airport = repository.findById(code);
        if (airport.isEmpty()) { return false; }
        airport.get().setName(name);
        airport.get().setCountryCode(country);
        airport.get().setLatitude(Math.round(latitude * 1000000d) / 1000000d);
        airport.get().setLongtitude(Math.round(longtitude * 1000000d) / 1000000d);
        airport.get().setTimeZone(timeZone);
        repository.save(airport.get());
        return true;
    }
    public boolean deleteAirport(String code){
        Optional<Airport> airport = repository.findById(code);
        if (airport.isPresent()) {
            repository.delete(airport.get());
            return true;
        }
        return false;
    }
}