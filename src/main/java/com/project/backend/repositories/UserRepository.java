package com.project.backend.repositories;

import com.project.backend.models.DTO.*;
import com.project.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from user where username = :userName",nativeQuery = true)
    Optional<User> findByUserName(@Param("userName") String userName);

    @Query(value = "SELECT YEAR(regis_date) as year,MONTH(regis_date) as month,COUNT(*) as new_user_amount FROM user\n" +
            "GROUP BY YEAR(regis_date),MONTH(regis_date)" +
            "ORDER BY YEAR(regis_date),MONTH(regis_date);",nativeQuery = true)
    List<MonthlyUserDTO> getMonthlyUser();

    @Query(value = "SELECT YEAR(regis_date) as year,QUARTER(regis_date) as quarter,COUNT(*) as new_user_amount FROM user\n" +
            "GROUP BY YEAR(regis_date),QUARTER(regis_date)" +
            "ORDER BY YEAR(regis_date),QUARTER(regis_date);",nativeQuery = true)
    List<QuarterlyUserDTO> getQuarterUser();

    @Query(value = "SELECT MONTH(date_of_birth) as month_of_birth,COUNT(*) as user_count FROM user\n" +
            "GROUP BY MONTH(date_of_birth);",nativeQuery = true)
    List<MOBUserDTO> getMOBUserCount();

    @Query(value = "SELECT user.id as user_id,fd.code as route_code,\n" +
            "case when mod(fd.instance_id,2) = 0 then route.to_airport else route.from_airport end as from_airport,\n" +
            "case when mod(fd.instance_id,2) = 0 then route.from_airport else route.to_airport end as to_airport,\n" +
            "COUNT(ticket.id) as flight_amount FROM user\n" +
            "INNER JOIN ticket\n" +
            "ON ticket.user_id = user.id\n" +
            "INNER JOIN flight_data fd\n" +
            "ON fd.instance_id = ticket.instance_id\n" +
            "INNER JOIN route\n" +
            "ON route.code = fd.code\n" +
            "GROUP BY user.id,fd.instance_id;",nativeQuery = true)
    List<FrequentRouteDTO> getFrequentRoute();

    @Query(value = "SELECT user.id as user_id,voucher.promotion_id,COUNT(*) as use_count FROM user\n" +
            "INNER JOIN voucher\n" +
            "ON user.id = voucher.belong_to_user\n" +
            "WHERE voucher.is_used = 1\n" +
            "GROUP BY user.id,voucher.promotion_id;",nativeQuery = true)
    List<FrequentPromotionDTO> getFrequentPromotion();
}
