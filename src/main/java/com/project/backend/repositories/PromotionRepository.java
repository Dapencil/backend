package com.project.backend.repositories;

import com.project.backend.models.DTO.PromotionUsageMonthlyDTO;
import com.project.backend.models.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion,String> {

    @Query(value = "SELECT vd.promotion_id,YEAR(ticket.issued_date) as issued_year,MONTH(ticket.issued_date) as issued_month,COUNT(*) as used_amount FROM ticket\n" +
            "INNER JOIN voucher_discount vd\n" +
            "ON ticket.voucher_code = vd.code\n" +
            "GROUP BY vd.promotion_id,YEAR(ticket.issued_date),MONTH(ticket.issued_date);",nativeQuery = true)
    List<PromotionUsageMonthlyDTO> getMonthlyUsage();
}
