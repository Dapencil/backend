package com.project.backend.controllers;

import com.project.backend.services.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@Slf4j
public class FlightInstanceController {

    @Scheduled(cron = "0 0 4 1 * *")
    public void flightInstanceGenerator(){
        log.info("Hello at "+ ZonedDateTime.now());
    }
}
