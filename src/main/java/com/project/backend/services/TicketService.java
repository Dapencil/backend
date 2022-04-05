package com.project.backend.services;

import com.project.backend.models.Ticket;
import com.project.backend.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private TicketRepository repository;

    public Ticket findTicketById(Integer id) {
        return repository.findById(id).get();
    }

    public List<Ticket> getTicket() {
        return repository.findAll();
    }

    public boolean addTicket(Integer id, Integer instanceId, Integer userId, String voucherCode, LocalDateTime issuedDate){
        Ticket ticket = new Ticket();
        ticket.setTicketId(id);
        ticket.setInstanceId(instanceId);
        ticket.setUserId(userId);
        ticket.setVoucherCode(voucherCode);
        ticket.setIssuedDate(issuedDate);
        repository.save(ticket);
        return true;
    }

    public boolean updateTicket(Integer id, Integer instanceId, Integer userId, String voucherCode, LocalDateTime issuedDate){
        Optional<Ticket> ticket = repository.findById(id);
        if(ticket.isEmpty()) {return false;}
        ticket.get().setInstanceId(instanceId);
        ticket.get().setUserId(userId);
        ticket.get().setVoucherCode(voucherCode);
        ticket.get().setIssuedDate(issuedDate);
        return true;
    }

    public boolean deleteTicket(Integer id){
        Optional<Ticket> ticket = repository.findById(id);
        if(ticket.isPresent()){
            repository.delete(ticket.get());
            return true;
        }
        return false;
    }
}
