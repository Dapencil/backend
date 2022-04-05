package com.project.backend.controllers;

import com.project.backend.Util.UtilHelper;
import com.project.backend.models.Country;
import com.project.backend.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("")
    public List<Country> getAll(){
        return countryService.getAll();
    }

    @GetMapping("{code}")
    @ResponseBody
    public ResponseEntity getByCode(@PathVariable String code){
        try {
            Country item = countryService.findByCode(code);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity addCountry(@RequestBody Country country){
        try{
            Country item = countryService.add(country);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @PutMapping("/{code}")
    @ResponseBody
    public ResponseEntity updateCountry(@RequestBody Country country, @PathVariable String code){
        try{
            Country item = countryService.update(country,code);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @DeleteMapping("/{code}")
    @ResponseBody
    public ResponseEntity deleteCountry(@PathVariable String code){
        try{
            Country item = countryService.delete(code);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }
}
