package com.project.backend.services;

import com.project.backend.models.Flight;
import com.project.backend.models.Route;
import com.project.backend.repositories.FlightRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class FlightService {

    @Autowired
    private FlightRepository repository;

    @Autowired
    private RouteService routeService;


    public List<Flight> getAllFlight(){
        return repository.findAll();
    }

    public void addFlight(String routeCode,String planeCode,String departureTime,Double fare){
        Flight flight = new Flight();
        flight.setRouteCode(routeCode);
        flight.setPlaneCode(planeCode);
        flight.setDepartureTime(departureTime);
        flight.setFare(fare);
        repository.save(flight);
    }

    private int getTakenTimeFromRoute(String routeCode){
        Route route = routeService.getAllRoute().stream().filter(t -> t.getCode().equals(routeCode)).collect(Collectors.toList()).get(0);
        return route.getTakenTime();
    }

    public String  backFlightDepartTime(String routeCode,String departureTime) {
        try {
            Date date = new SimpleDateFormat("HH:mm:ss").parse(departureTime);
            Calendar calendar = Calendar.getInstance();
            int temp = 60+getTakenTimeFromRoute(routeCode);
            calendar.setTime(date);
            calendar.add(Calendar.MINUTE,temp);
            SimpleDateFormat converter = new SimpleDateFormat("HH:mm:ss");
            return converter.format(calendar.getTime());
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
