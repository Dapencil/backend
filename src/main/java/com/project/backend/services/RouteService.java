package com.project.backend.services;

import com.project.backend.models.Airport;
import com.project.backend.models.Route;
import com.project.backend.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RouteService {

    final static double planeVelocity = 575d; // mile per hour
    final static int mileConverter = 3956;

    @Autowired
    private RouteRepository repository;


    public List<Route> getAll(){
        return repository.findAll();
    }

    public Route findByCode(String code){
        Route item = repository.findById(code)
                    .orElseThrow(() -> new NoSuchElementException("Doesn't exist"));
        return item;
    }

    public Route addRoute(Route route,Airport from,Airport to){
        try {
            isPresent(route.getCode());
            codeValidation(route.getCode());
            airportValidation(route.getToAirport());
            airportValidation(route.getFromAirport());

            Integer distance = distanceFromAirport(from,to);
            Integer takenTime = takenTime(distance);

            route.setDistance(distance);
            route.setTakenTime(takenTime);
            return repository.save(route);
        }catch (Exception e){
            throw e;
        }
    }

    public Route updateRoute(Route newItem,Airport from,Airport to,String code) {
        try{
            Route item = findByCode(code);
            airportValidation(newItem.getToAirport());
            airportValidation(newItem.getFromAirport());

            Integer distance = distanceFromAirport(from,to);
            Integer takenTime = takenTime(distance);

            item.setFromAirport(newItem.getFromAirport());
            item.setToAirport(newItem.getToAirport());
            item.setDistance(distance);
            item.setTakenTime(takenTime);
            return repository.save(item);
        }catch (Exception e){
            throw e;
        }
    }

    public Route deleteRoute(String code) {
        Route item = findByCode(code);
        repository.delete(item);
        return item;
    }

    public int getTakenTimeFromRoute(String routeCode){
        return findByCode(routeCode).getTakenTime();
    }

    //validate CUXXX
    private boolean codeValidation(String code){
        final String regex = "CU[0-9]{3}";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(code);
        if(!matcher.matches()){
            throw new IllegalArgumentException("invalid route code");
        }
        return matcher.matches();
    }

    private boolean airportValidation(String code){
        final String regex = "[A-Z]{3}";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(code);

        if(!matcher.matches()){
            throw new IllegalArgumentException("invalid airport code");
        }

        return matcher.matches();
    }


    private Integer takenTime(double distance){
        return (int) (distance / planeVelocity) * 60;
    }

    private Integer distanceFromAirport(Airport from,Airport to){
        Integer distance;
        double fromLongtitude = Math.toRadians(from.getLongtitude());
        double toLongtitude = Math.toRadians(to.getLongtitude());
        double fromLatitude = Math.toRadians(from.getLatitude());
        double toLatitude = Math.toRadians(to.getLatitude());

        double distanceLongtitude = fromLongtitude - toLongtitude;
        double distanceLatitude = fromLatitude - toLatitude;

        double a = Math.pow(Math.sin(distanceLatitude / 2), 2)
                + Math.cos(fromLatitude) * Math.cos(toLatitude)
                * Math.pow(Math.sin(distanceLongtitude / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        distance = (int)(c* mileConverter);

        return distance;
    }

    private void isPresent(String code){
        if(repository.findById(code).isPresent()){
            throw new IllegalArgumentException("This item has been in db.");
        }
    }
}
