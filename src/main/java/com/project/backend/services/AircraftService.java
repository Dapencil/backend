package com.project.backend.services;

import com.project.backend.models.Aircraft;
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

    public Aircraft update(Aircraft old,String regNum) throws EntityNotFoundException {
        Aircraft toBeUpdate = repository.findById(regNum)
                .orElseThrow(() -> new NoSuchElementException("aircraft not found."));
        toBeUpdate.setICAOCode(old.getICAOCode());
        toBeUpdate.setMSN(old.getMSN());
        toBeUpdate.setDeliverDate(old.getDeliverDate());
        toBeUpdate.setFirstFlight(old.getFirstFlight());
        return repository.save(toBeUpdate);

    }

    public Aircraft delete(String regNum) {
        Aircraft item = repository.findById(regNum)
                .orElseThrow(() -> (new NoSuchElementException("aircraft not found.")));
        repository.delete(item);
        return item;
    }

}
