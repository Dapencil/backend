package com.project.backend.services;

import com.project.backend.models.Aircraft;
import com.project.backend.repositories.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AircraftService {

    @Autowired
    private AircraftRepository repository;
    public Aircraft findAircraftByCode(String code) {
        Optional<Aircraft> aircraft = repository.findById(code);
        if(aircraft.isEmpty()) { throw new RuntimeException("The aircraft doesn't exist"); }
        return repository.findById(code).get();
    }

    public List<Aircraft> getAircraft() {
        return repository.findAll();
    }
    public boolean addAircraft(String regNum, String icaoCode, String msn, LocalDate fFlight, LocalDate dFlight){
        if (!checkCode(regNum)) { return false; }
        Aircraft aircraft = new Aircraft();
        aircraft.setRegNum(regNum);
        aircraft.setICAOCode(icaoCode);
        aircraft.setMsn(msn);
        aircraft.setFFlight(fFlight);
        aircraft.setDFlight(dFlight);
        repository.save(aircraft);
        return true;
    }
    public boolean checkCode(String s){ //check HS-XXX
        final String regex = "HS-[A-Z]{3}";
        final String string = s;
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
    public boolean updateAircraft(String regNum, String icaoCode, String msn, LocalDate fFlight, LocalDate dFlight) {
        Optional<Aircraft> aircraft = repository.findById(regNum);
        if(aircraft.isEmpty()) { return false; }
        aircraft.get().setICAOCode(icaoCode);
        aircraft.get().setMsn(msn);
        aircraft.get().setFFlight(fFlight);
        aircraft.get().setDFlight(dFlight);
        repository.save(aircraft.get());
        return true;
    }
    public boolean deletePlane(String code) {
        Optional<Aircraft> aircraft = repository.findById(code);
        if (aircraft.isPresent()) {
            repository.delete(aircraft.get());
            return true;
        }
        return false;
    }
}
