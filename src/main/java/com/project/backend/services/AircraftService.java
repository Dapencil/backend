package com.project.backend.services;

import com.project.backend.models.Aircraft;
import com.project.backend.models.Airport;
import com.project.backend.repositories.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AircraftService {

    @Autowired
    private AircraftRepository repository;

    public Aircraft findByRegNum(String regNum) {
        Aircraft item = repository.findById(regNum)
                .orElseThrow(() -> new NoSuchElementException("Doesn't exist"));
        return item;
    }

    public List<Aircraft> getAll() {
        return repository.findAll();
    }

    public Aircraft add(Aircraft newAircraft){
        return repository.save(newAircraft);
    }

    public Aircraft update(Aircraft newItem,String regNum){
        try{
            Aircraft item = findByRegNum(regNum);
            item.setICAOCode(newItem.getICAOCode());
            item.setMSN(newItem.getMSN());
            item.setDeliverDate(newItem.getDeliverDate());
            item.setFirstFlight(newItem.getFirstFlight());
            return repository.save(item);
        }catch (Exception e){
            throw e;
        }
    }

    public Aircraft delete(String regNum) {
        Aircraft item = findByRegNum(regNum);
        repository.delete(item);
        return item;
    }

}
