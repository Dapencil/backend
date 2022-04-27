package com.project.backend.models.ResponseModel;

import com.project.backend.models.Ticket;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TicketResponse {

    private Integer ticketId;
    private Integer instanceId;
    private Integer userId;
    private String voucherCode;
    private LocalDateTime issuedDate;
    private Integer discount;

    public TicketResponse(Ticket ticket,Integer discount){
        this.ticketId = ticket.getTicketId();
        this.instanceId = ticket.getInstanceId();
        this.userId = ticket.getUserId();
        this.voucherCode = ticket.getVoucherCode();
        this.issuedDate = ticket.getIssuedDate();
        this.discount = discount;
    }
}
