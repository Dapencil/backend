package com.project.backend.models;

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
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "voucher")
public class Voucher {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "belong_to_user")
    private Integer belongToUser;

    @Column(name = "promotion_id")
    private String promotionId;

    @Column(name = "mile_before_exchage")
    private Integer mileBefore;

    @Column(name = "issued_date")
    private LocalDateTime issuedDate;

    @Column(name = "valid_until")
    private LocalDateTime validUntil;

    @Column(name = "is_used")
    private Boolean isUsed;

}
