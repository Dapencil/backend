package com.project.backend.task;

import com.project.backend.services.FlightInstanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class FITask {

    @Autowired
    private FlightInstanceService flightInstanceService;

    //every 4am on first date
    @Scheduled(cron = "0 0 4 1 * *")
    public void flightInstanceGenerator(){
        log.info("Monthly FI generate...");
        flightInstanceService.instanceGenerator();
    }

}
