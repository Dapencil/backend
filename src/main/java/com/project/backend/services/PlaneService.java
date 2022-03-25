package com.project.backend.services;

import com.project.backend.models.Airport;
import com.project.backend.models.Plane;
import com.project.backend.repositories.AirportRepository;
import com.project.backend.repositories.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PlaneService {

    @Autowired
    private PlaneRepository repository;

    public Plane findPlaneByCode(String code) {
        return repository.findById(code).get();
    }

    public List<Plane> getPlane() {
        return repository.findAll();
    }
    public int addPlane(String code, String model, int capacity){
        if (!checkCode(code)) { return 0; }
        Plane plane = new Plane();
        plane.setCode(code);
        plane.setModel(model);
        plane.setCapacity(capacity);
        repository.save(plane);
        return 1;
    }
    public boolean checkCode(String s){ //check CS-XXX
        final String regex = "CS-[A-Z]{3}";
        final String string = s;
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
