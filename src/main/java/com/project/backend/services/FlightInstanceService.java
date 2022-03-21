package com.project.backend.services;

import com.project.backend.models.Flight;
import com.project.backend.models.FlightInstance;
import com.project.backend.repositories.FlightInstanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FlightInstanceService {

    @Autowired
    private FlightInstanceRepository repository;

    @Autowired
    private FlightService flightService;

    @Autowired
    private RouteService routeService;

    public void instanceGenerator(){
        List<Flight> flightList = flightService.getAllFlight();
        for (int i = 1; i <= flightList.size(); i+=2) {
            Flight toFLight = flightList.get(i-1);
            Flight returnFlight = flightList.get(i);
            int takenTime = getTakenTimeFromFlight(toFLight);

            FlightInstance recent = getRecentInstance(returnFlight.getFlightId()+"");

            LocalTime toTime = timeFormatter(toFLight.getDepartureTime());
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

    public void firstInstanceGenerator(){
        List<Flight> flightList = flightService.getAllFlight();
        for (int i = 1; i <= flightList.size(); i+=2) {
            //odd i-1
            //even i
            Flight toFLight = flightList.get(i-1);
            Flight returnFlight = flightList.get(i);
            int takenTime = routeService.getAllRoute().stream().filter(route -> route.getCode().equals(toFLight.getRouteCode()))
                                        .collect(Collectors.toList()).get(0).getTakenTime();

            //ตอนนี้ต้องหาวันที่มากที่สุดแล้ว
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime toTime = LocalTime.parse(toFLight.getDepartureTime(),formatter);
            LocalDateTime toDate = LocalDateTime.now().with(toTime).plusDays(1);
            LocalDateTime returnDate = toDate.plusMinutes(takenTime+60);
            LocalDateTime arrivalReturnDate = returnDate.plusMinutes(takenTime);
            FlightInstance recent = getRecentInstance(returnFlight.getFlightId()+"");
            log.info(recent.getInstanceId()+" "+recent.getFlightId()+" "+recent.getFlightDate());

            boolean condition = toDate.getHour() < arrivalReturnDate.getHour();
//            log.info(toFLight.getFlightId().toString());
//            log.info(returnFlight.getFlightId().toString());
//            log.info(""+toDate.getHour()+" "+returnDate.getHour());
//            log.info(""+condition);

//            for(int day = 1;day<=90;day++){
//                addInstance(toFLight.getFlightId(),toDate);
//                addInstance(returnFlight.getFlightId(),returnDate);
//                if(condition){
//                    toDate = toDate.withDayOfYear(returnDate.getDayOfYear()).plusDays(1);
//                }else toDate = toDate.withDayOfYear(returnDate.getDayOfYear());
//                returnDate = toDate.plusMinutes(takenTime+60);
//            }
        }
    }

    private void addInstance(int flightId,LocalDateTime date){
        FlightInstance instance = new FlightInstance();
        instance.setFlightId(flightId);
        instance.setFlightDate(date);
        repository.save(instance);
    }

    private FlightInstance getRecentInstance(String flightId){
        FlightInstance recentInstance = repository.getRecent(flightId);
        return recentInstance;
    }

    private int getTakenTimeFromFlight(Flight toFlight){
        int takenTime = routeService.getAllRoute().stream().filter(route -> route.getCode().equals(toFlight.getRouteCode()))
                .collect(Collectors.toList()).get(0).getTakenTime();
        return takenTime;
    }

    private LocalTime timeFormatter(String timeString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime time = LocalTime.parse(timeString,formatter);
        return time;
    }

}
