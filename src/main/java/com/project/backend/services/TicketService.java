package com.project.backend.services;

import com.project.backend.models.Ticket;
import com.project.backend.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository repository;

    public List<Ticket> getAll() {
        return repository.findAll();
    }

    public Ticket findById(Integer id) {
        Ticket item = repository.findById(id)
                      .orElseThrow(() -> new NoSuchElementException("Doesn't exist"));
        return item;
    }

    public Ticket add(Ticket ticket){
        try{
            voucherValidation(ticket.getVoucherCode());
            return repository.save(ticket);
        }catch (Exception e){
            throw e;
        }
    }

    public Ticket update(Ticket newItem,Integer id){
        try {
            Ticket item = findById(id);
            voucherValidation(newItem.getVoucherCode());

            item.setIssuedDate(newItem.getIssuedDate());
            item.setInstanceId(newItem.getInstanceId());
            item.setUserId(newItem.getUserId());
            item.setVoucherCode(newItem.getVoucherCode());
            return repository.save(item);
        }catch (Exception e){
            throw e;
        }
    }

    public Ticket delete(Integer id){
        Ticket item = findById(id);
        repository.delete(item);
        return item;
    }

    private boolean voucherValidation(String code){
        if(code.equals("") || code.length()==10){
            return true;
        }else throw new IllegalArgumentException("voucher code is not match pattern");
    }
}
