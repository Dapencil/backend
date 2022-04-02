package com.project.backend.controllers;

import com.project.backend.models.Airport;
import com.project.backend.models.Country;
import com.project.backend.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/country")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping("getAll")
    public List<Country> getCountry(){
        return countryService.getCountry();
    }
    @PostMapping("add")
    public boolean addCountry(@RequestBody Country country){
        return countryService.addCountry(country.getCode(),country.getName(),country.getContinent());
    }
    @PutMapping("update/{code}")
    public boolean updateCountry(@RequestBody Country country, @PathVariable String code){
        return countryService.updateCountry(country.getCode(),country.getName(),country.getContinent());
    }
    @DeleteMapping("delete/{code}")
    public boolean deleteCountry(@PathVariable String code){
        return countryService.deleteCountry(code);
    }
}
