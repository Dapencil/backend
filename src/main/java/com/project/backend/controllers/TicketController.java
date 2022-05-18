package com.project.backend.controllers;

import com.project.backend.Util.UtilHelper;
import com.project.backend.models.Ticket;
import com.project.backend.services.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

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
    @PreAuthorize("@secService.isMyPage(authentication,#ticket.userId) or hasRole('MANAGER')")
    @ResponseBody
    public ResponseEntity addTicket(@RequestBody Ticket ticket){
        try{
            Ticket item = ticketService.add(ticket);
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
