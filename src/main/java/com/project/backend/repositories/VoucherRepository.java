package com.project.backend.repositories;

import com.project.backend.models.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface VoucherRepository extends JpaRepository<Voucher,String> {

    @Query(value = "select * from voucher where belong_to_user = :userId and promotion_id = :promotionId and MONTH(issued_date) = MONTH(now()) and YEAR(issued_date) = YEAR(now())", nativeQuery = true)
    Optional<Voucher> checkPromotion(@Param("promotionId") String promotionId, @Param("userId") Integer userId);
}
