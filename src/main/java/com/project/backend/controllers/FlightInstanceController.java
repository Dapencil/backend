package com.project.backend.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@Slf4j
public class FlightInstanceController {

    //FIXME change call func
    @Scheduled(cron = "0 0 4 1 * *")
    public void flightInstanceGenerator(){
        log.info("Hello at "+ ZonedDateTime.now());
    }
}
