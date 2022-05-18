package com.project.backend.repositories;

import com.project.backend.models.DTO.AVGMileDTO;
import com.project.backend.models.DTO.VoucherDTO;
import com.project.backend.models.FlightInstance;
import com.project.backend.models.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface VoucherRepository extends JpaRepository<Voucher,String> {

    @Query(value = "select * from voucher where belong_to_user = :userId and promotion_id = :promotionId and MONTH(issued_date) = MONTH(now()) and YEAR(issued_date) = YEAR(now())", nativeQuery = true)
    Optional<Voucher> checkPromotion(@Param("promotionId") String promotionId, @Param("userId") Integer userId);

    @Query(value = "select title,code,valid_until from voucher inner join promotion on promotion.id = voucher.promotion_id where belong_to_user = :userId and is_used = 0",nativeQuery = true)
    List<VoucherDTO> getByUserID(@Param("userId") Integer userId);

    @Query(value = "SELECT promotion_id,COUNT(*) as n,AVG(mile_before_exchange) as avg_mile_before, MAX(mile_before_exchange) as max_mile, MIN(mile_before_exchange) as min_mile FROM voucher\n" +
            "WHERE promotion_id IN ('p_002','p_003','p_004')\n" +
            "GROUP BY promotion_id;",
            nativeQuery = true)
    List<AVGMileDTO> getAVGMile();

}
