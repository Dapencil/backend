package com.project.backend.services;

import com.project.backend.models.Flight;
import com.project.backend.repositories.FlightRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class FlightService {

    @Autowired
    private FlightRepository repository;

    @Autowired
    private RouteService routeService;

    @Autowired
    @Lazy
    private FlightInstanceService fiService;


    public List<Flight> getAll(){
        return repository.findAll();
    }

    public Flight findById(Integer id){
        Flight item = repository.findById(id)
                      .orElseThrow(() -> new IllegalArgumentException("Doesn't exist"));
        return item;
    }

    public List<Flight> add(Flight flight){
        try{

            fareValidation(flight.getFare());
            List<Flight> data = new ArrayList<>();
            Flight departureFlight = new Flight(flight.getFlightId(),flight.getRouteCode(),flight.getICAOCode(),flight.getDepartureTime(),flight.getFare())
                    ,arrivalFlight = new Flight(flight.getFlightId(),flight.getRouteCode(),flight.getICAOCode(),arrivalFlightTime(flight),flight.getFare());
            departureFlight = repository.save(departureFlight);
            arrivalFlight = repository.save(arrivalFlight);

            fiService.firstInstanceGenerator(departureFlight,arrivalFlight);
            data.add(departureFlight);
            data.add(arrivalFlight);
            return data;
        }catch (Exception e){
            throw e;
        }
    }

    public List<Flight> update(Flight newItem,Integer id) {
        try{
            List<Flight> data = new ArrayList<>();
            fareValidation(newItem.getFare());
            Flight item = findById(id);
            Flight siblingItem = findById(id%2==0? id-1:id+1);

            item.setFare(newItem.getFare());
            siblingItem.setFare(newItem.getFare());

            item.setRouteCode(newItem.getRouteCode());
            siblingItem.setRouteCode(newItem.getRouteCode());

            item.setICAOCode(newItem.getRouteCode());
            siblingItem.setICAOCode(newItem.getRouteCode());

            item.setDepartureTime(newItem.getDepartureTime());
            if(id%2==0){
                siblingItem.setDepartureTime(departureFlightTime(newItem));
            }else{
                siblingItem.setDepartureTime(arrivalFlightTime(newItem));
            }

            data.add(repository.save(item));
            data.add(repository.save(siblingItem));
            return data;
        }catch (Exception e){
            throw e;
        }
    }

    public List<Flight> delete(Integer id) {
        List<Flight> data = new ArrayList<>();
        Flight item = findById(id);
        Flight siblingItem = findById(id%2==0? id-1:id+1);
        data.add(item);
        data.add(siblingItem);
        repository.delete(item);
        repository.delete(siblingItem);
        return  data;
    }

    private LocalTime arrivalFlightTime(Flight departureFlight) {
        String routeCode = departureFlight.getRouteCode();
        LocalTime arrivalTime = departureFlight.getDepartureTime()
                                .plusMinutes(60+getTakenTimeFromRoute(routeCode));
        return arrivalTime;
    }

    private LocalTime departureFlightTime(Flight arrivalFlight) {
        String routeCode = arrivalFlight.getRouteCode();
        LocalTime departureTime = arrivalFlight.getDepartureTime()
                .minusMinutes(60+getTakenTimeFromRoute(routeCode));
        return departureTime;
    }

    private int getTakenTimeFromRoute(String routeCode){
        return routeService.getTakenTimeFromRoute(routeCode);
    }

    private boolean fareValidation(Double fare){
        if(fare < 0) throw new IllegalArgumentException("Fare must be positive");
        return true;
    }

    private void isPresent(Integer id){
        if(repository.findById(id).isPresent()){
            throw new IllegalArgumentException("This item has been in db.");
        }
    }



}
