package com.project.backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
@AllArgsConstructor
@Table(name = "ticket")
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

    @Column(name = "issued_date")
    private LocalDateTime issuedDate;
}
