package com.project.backend.controllers;

import com.project.backend.Util.UtilHelper;
import com.project.backend.models.ResponseModel.AvailableFlight;
import com.project.backend.models.Ticket;
import com.project.backend.sec.CustomUserDetails;
import com.project.backend.services.EmailService;
import com.project.backend.services.ResService.AvailableFlightService;
import com.project.backend.services.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AvailableFlightService availableFlightService;

    @GetMapping("")
    public List<Ticket> getTicket() {
        return ticketService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity getTicketById(@PathVariable Integer id){
        try{
            Ticket item = ticketService.findById(id);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    //TODO MUST CHECK THAT THEY DON"T CREATE FOR ANOTHER USER
    @PostMapping("")
    @ResponseBody
    public ResponseEntity addTicket(@RequestBody Ticket ticket){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
            ticket.setUserId(details.getId());
            Ticket item = ticketService.add(ticket);
            AvailableFlight temp = availableFlightService.getById(item.getInstanceId());
            emailService.sendTicketEmail(details.getUsername(),item.getTicketId(),details.getFullName(),temp.getFlightId(),temp.getFlightDate(),temp.getFrom()+" "+temp.getTo());
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity updateTicket(@RequestBody Ticket ticket, @PathVariable Integer id){
        try{
            Ticket item = ticketService.update(ticket,id);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity deleteTicket(@PathVariable Integer id){
        try{
            Ticket item = ticketService.delete(id);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }
}
