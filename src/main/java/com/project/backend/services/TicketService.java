package com.project.backend.services;

import com.project.backend.models.Ticket;
import com.project.backend.models.Voucher;
import com.project.backend.repositories.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class TicketService {

    @Autowired
    private TicketRepository repository;

    @Autowired
    private FlightInstanceService fiService;

    @Autowired
    private VoucherService voucherService;

    public List<Ticket> getAll() {
        return repository.findAll();
    }

    public Ticket findById(Integer id) {
        Ticket item = repository.findById(id)
                      .orElseThrow(() -> new NoSuchElementException("Doesn't exist"));
        return item;
    }

    //TODO ADD MILE TO USER
    public Ticket add(Ticket ticket){
        try{
            isAbleToBook(ticket.getInstanceId());
            voucherValidation(ticket.getVoucherCode());
            if(!ticket.getVoucherCode().isBlank()) {
                log.info("I'm in");
                Voucher item = voucherService.findByCode(ticket.getVoucherCode());
                item.setIsUsed(true);
                voucherService.update(item,item.getCode());
            }else{
                ticket.setVoucherCode(null);
            }
            log.info("Out");
            ticket.setIssuedDate(LocalDateTime.now());
            return repository.save(ticket);
        }catch (Exception e){
            throw e;
        }
    }

    public Ticket update(Ticket newItem,Integer id){
        try {
            Ticket item = findById(id);
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

    private boolean isAbleToBook(Integer instanceId){
        Integer seats = fiService.getSeats(instanceId);
        Integer ticketCount = repository.getTicketCount(instanceId);
        if(seats > ticketCount) {
            log.info("can book");
            return true;
        }
        else throw new IllegalArgumentException("Can't complete booking because seats reached maximum");
    }

    private boolean voucherValidation(String code){
        log.info("code validation");
        log.info(String.valueOf(code.isBlank()));
        if(code.isBlank() || code.length()==10) return true;
        else throw new IllegalArgumentException("voucher code is not match pattern");
    }
}
