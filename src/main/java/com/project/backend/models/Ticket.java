package com.project.backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="ticketSequenceGenerator")
    @SequenceGenerator(allocationSize=1, name="ticketSequenceGenerator", sequenceName = "ticket_sequence")
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
