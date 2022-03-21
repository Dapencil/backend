package com.project.backend.services;

import com.project.backend.models.Airport;
import com.project.backend.models.Route;
import com.project.backend.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {
    final static double planeVelocity = 575d; // mile per minute
    final static int mileConverter = 3956;

    @Autowired
    private RouteRepository repository;

    public Route addRoute(String routeCode,Airport fromAirport,Airport toAirport){
        Route route = new Route();
        Integer distance = distanceFromAirport(fromAirport,toAirport);
        Integer takenTime = takenTime(distance);
        route.setCode(routeCode);
        route.setFromAirport(fromAirport.getCode());
        route.setToAirport(toAirport.getCode());
        route.setDistance(distance);
        route.setTakenTime(takenTime);
        repository.save(route);
        return route;
    }

    public List<Route> getAllRoute(){
        return repository.findAll();
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

    private Integer takenTime(double distance){
        return (int) ((double)distance / planeVelocity) * 60;
    }
}
