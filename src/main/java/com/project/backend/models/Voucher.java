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
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vouchers")
public class Voucher {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "belong_to_user")
    private Integer belongToUser;

    @Column(name = "promotion_id")
    private Integer promotionId;

    @Column(name = "is_used")
    private Boolean isUsed;

}
