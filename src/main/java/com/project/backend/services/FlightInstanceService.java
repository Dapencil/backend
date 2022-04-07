package com.project.backend.services;

import com.project.backend.models.Flight;
import com.project.backend.models.FlightInstance;
import com.project.backend.repositories.FlightInstanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FlightInstanceService {

    @Autowired
    private FlightInstanceRepository repository;

    @Autowired
    @Lazy
    private FlightService flightService;

    @Autowired
    private RouteService routeService;

    public List<FlightInstance> getAll(){
        return repository.findAll();
    }

    public FlightInstance findById(Integer id){
        FlightInstance item = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Doesn't exist"));
        return item;
    }

    public FlightInstance update(FlightInstance newItem,Integer id){
        FlightInstance item = findById(id);
        item.setAircraftRegNum(newItem.getAircraftRegNum());
        return repository.save(item);
    }

    public FlightInstance delete(Integer id){
        FlightInstance item = findById(id);
        repository.delete(item);
        return item;
    }

    //monthly generate
    public void instanceGenerator(){
        List<Flight> flightList = flightService.getAll();
        for (int i = 1; i <= flightList.size(); i+=2) {
            Flight toFLight = flightList.get(i-1);
            Flight returnFlight = flightList.get(i);
            int takenTime = getTakenTimeFromFlight(toFLight);

            FlightInstance recent = getRecentInstance(returnFlight.getFlightId()+"");

            LocalTime toTime = toFLight.getDepartureTime();
            LocalDateTime toDate = recent.getFlightDate();
            LocalDateTime returnDate;
            boolean condition = toTime.getHour() < recent.getFlightDate().plusMinutes(takenTime).getHour();

            if(condition){
                toDate = toDate.with(toTime).plusDays(1);
            }else toDate = toDate.with(toTime);
            returnDate = toDate.plusMinutes(takenTime+60);

            for(int day = 1;day<=90;day++){
                addInstance(toFLight.getFlightId(),toDate);
                addInstance(returnFlight.getFlightId(),returnDate);
                if(condition){
                    toDate = toDate.withDayOfYear(returnDate.getDayOfYear()).plusDays(1);
                }else toDate = toDate.withDayOfYear(returnDate.getDayOfYear());
                returnDate = toDate.plusMinutes(takenTime+60);
            }

        }
    }

    //TODO maybe fix
    public void firstInstanceGenerator(Flight toFlight,Flight returnFlight){

        int takenTime = routeService.getTakenTimeFromRoute(toFlight.getRouteCode());

        LocalTime toTime = toFlight.getDepartureTime();
        LocalDateTime toDate = LocalDateTime.now().with(toTime).plusDays(1);
        LocalDateTime returnDate = toDate.plusMinutes(takenTime+60);
        LocalDateTime arrivalReturnDate = returnDate.plusMinutes(takenTime);

        boolean condition = toDate.getHour() < arrivalReturnDate.getHour();

        if(condition){
            toDate = toDate.with(toTime).plusDays(1);
        }else toDate = toDate.with(toTime);
        returnDate = toDate.plusMinutes(takenTime+60);

        for(int day = 1;day<=90;day++){
            addInstance(toFlight.getFlightId(),toDate);
            addInstance(returnFlight.getFlightId(),returnDate);
            if(condition){
                toDate = toDate.withDayOfYear(returnDate.getDayOfYear()).plusDays(1);
            }else toDate = toDate.withDayOfYear(returnDate.getDayOfYear());
            returnDate = toDate.plusMinutes(takenTime+60);
        }
    }

    //TODO make this method
    private void addInstance(int flightId,LocalDateTime date){
        FlightInstance instance = new FlightInstance();
        instance.setFlightId(flightId);
        instance.setFlightDate(date);
        instance.setAircraftRegNum("TBA");
        repository.save(instance);
    }

    private FlightInstance getRecentInstance(String flightId){
        FlightInstance recentInstance = repository.getRecent(flightId);
        return recentInstance;
    }

    private int getTakenTimeFromFlight(Flight toFlight){
        int takenTime = routeService.getAll().stream().filter(route -> route.getCode().equals(toFlight.getRouteCode()))
                .collect(Collectors.toList()).get(0).getTakenTime();
        return takenTime;
    }
}
