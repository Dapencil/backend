package com.project.backend.services;

import com.project.backend.models.Country;
import com.project.backend.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CountryService {
    @Autowired
    private CountryRepository repository;
    public List<Country> getCountry(){ return repository.findAll(); }
    public Country getCountryByCode(String code){
        return repository.getById(code);
    }

    public boolean addCountry(String code, String name, String continent){
        if (!checkCode(code)) { return false; }
        Country country = new Country();
        country.setCode(code);
        country.setName(name);
        country.setContinent(continent);
        repository.save(country);
        return true;
    }
    public boolean checkCode(String s){ //check A-Z 3 char
        final String regex = "[A-Z]{3}";
        final String string = s;
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
    public boolean updateCountry(String code, String name, String continent){
        Optional<Country> country = repository.findById(code);
        if (country.isEmpty()) { return false; }
        country.get().setName(name);
        country.get().setContinent(continent);
        repository.save(country.get());
        return true;
    }
    public boolean deleteCountry(String code){
        Optional<Country> country = repository.findById(code);
        if (country.isPresent()){
            repository.delete(country.get());
            return true;
        }
        return false;
    }

}
