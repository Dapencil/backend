package com.project.backend.services;

import com.project.backend.models.Country;
import com.project.backend.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CountryService {
    @Autowired
    private CountryRepository repository;

    public List<Country> getAll(){
        return repository.findAll();
    }

    public Country findByCode(String code){
        Country item = repository.findById(code)
                .orElseThrow(() -> new NoSuchElementException("Doesn't exist"));
        return item;
    }

    public Country add(Country country){
        try {
            codeValidation(country.getCode());
            continentValidation(country.getContinent());
            return repository.save(country);
        }catch (Exception e){
            throw e;
        }
    }

    public Country update(Country newItem,String code){
        try{
            Country item = findByCode(code);
            codeValidation(newItem.getCode());
            continentValidation(newItem.getContinent());
            item.setName(newItem.getName());
            item.setContinent(newItem.getContinent());
            return repository.save(item);
        }catch (Exception e){
            throw e;
        }
    }

    public Country delete(String code){
        Country item = findByCode(code);
        repository.delete(item);
        return item;
    }

    private boolean codeValidation(String code){
        final String regex = "[A-Z]{3}";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(code);

        if(!matcher.matches()){
            throw new IllegalArgumentException("invalid country code");
        }
        return matcher.matches();
    }

    private boolean continentValidation(String continent){
        final String regex = "[A-Z]{2}";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(continent);

        if(!matcher.matches()){
            throw new IllegalArgumentException("invalid continent code");
        }
        return matcher.matches();
    }

}
