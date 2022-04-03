package com.project.backend.controllers;

import com.project.backend.models.Airport;
import com.project.backend.models.Ticket;
import com.project.backend.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("getAll")
    public List<Ticket> getTicket() {
        return ticketService.getTicket();
    }
    @GetMapping("get/{id}")
    public Ticket getTicketById(@PathVariable Integer id){
        return ticketService.findTicketById(id);
    }

    @PostMapping("add")
    public boolean addTicket(@RequestBody Ticket ticket){
        return ticketService.addTicket(ticket.getTicketId(),ticket.getInstanceId(),
                ticket.getUserId(),ticket.getVoucherCode(),ticket.getIssueDate());
    }
    @PutMapping("update/{id}")
    public boolean updateTicket(@RequestBody Ticket ticket, @PathVariable Integer id){
        return ticketService.updateTicket(ticket.getTicketId(),ticket.getInstanceId(),
                ticket.getUserId(),ticket.getVoucherCode(),ticket.getIssueDate());
    }
    @DeleteMapping("delete/{id}")
    public boolean deleteTicket(@PathVariable Integer id){
        return ticketService.deleteTicket(id);
    }
}
