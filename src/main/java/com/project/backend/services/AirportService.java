package com.project.backend.services;

import com.project.backend.models.Airport;
import com.project.backend.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Service
public class AirportService {

    @Autowired
    private AirportRepository repository;

    public List<Airport> getAll() {
        return repository.findAll();
    }

    public Airport findByCode(String code) {
        Airport item = repository.findById(code)
                .orElseThrow(() -> new NoSuchElementException("Doesn't exist"));
        return item;
    }

    public Airport add(Airport airport) {
        try{
            codeValidation(airport.getCode());
            countryCodeValidation(airport.getCountryCode());
            return repository.save(airport);
        }catch(Exception e){
            throw e;
        }
    }

    public Airport update(Airport newItem,String code){
        try{
            Airport item = findByCode(code);
            countryCodeValidation(newItem.getCountryCode());
            item.setTimeZone(newItem.getTimeZone());
            item.setCountryCode(newItem.getCountryCode());
            item.setName(newItem.getName());
            item.setLatitude(roundNumber(newItem.getLatitude()));
            item.setLongtitude(roundNumber(newItem.getLongtitude()));
            return repository.save(item);
        } catch (Exception e){
            throw e;
        }
    }

    public Airport delete(String code){
        Airport item = findByCode(code);
        repository.delete(item);
        return item;
    }

    //check A-Z 3 char
    private boolean codeValidation(String code){
        final String regex = "[A-Z]{3}";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(code);

        if(!matcher.matches()){
            throw new IllegalArgumentException("invalid airport code");
        }

        return matcher.matches();
    }

    //check A-Z 3 char
    private boolean countryCodeValidation(String code){
        final String regex = "[A-Z]{3}";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(code);

        if(!matcher.matches()){
            throw new IllegalArgumentException("invalid country code");
        }

        return matcher.matches();
    }

    private Double roundNumber(double number){
        return Math.round(number * 1000000d) / 1000000d;
    }

}