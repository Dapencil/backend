package com.project.backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tickets")
public class Ticket {

    @Id
    @Column(name = "id")
    private Integer ticketId;

    @Column(name = "instance_id")
    private Integer instanceId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "voucher_code")
    private String voucherCode;

}
