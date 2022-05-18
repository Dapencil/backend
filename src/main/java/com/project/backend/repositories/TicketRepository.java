package com.project.backend.repositories;

import com.project.backend.models.DTO.*;
import com.project.backend.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {

    @Procedure
    Integer getTicketCount(Integer instanceId);

    @Query(value = "SELECT YEAR(issued_date) as year,SUM(fd.fare) - SUM(IFNull(vd.discount_amount,0)) as income FROM ticket \n" +
            "INNER JOIN flight_data fd\n" +
            "ON fd.instance_id = ticket.instance_id\n" +
            "LEFT JOIN voucher_discount vd\n" +
            "ON vd.code = ticket.voucher_code\n" +
            "GROUP BY YEAR(ticket.issued_date); ", nativeQuery = true)
    List<AnnualyIncomeDTO> getAnnualIncome();

    @Query(value = "SELECT YEAR(issued_date) as year,QUARTER(issued_date) as quarter,SUM(fd.fare) - SUM(IFNull(vd.discount_amount,0)) as income FROM ticket \n" +
            "INNER JOIN flight_data fd\n" +
            "ON fd.instance_id = ticket.instance_id\n" +
            "LEFT JOIN voucher_discount vd\n" +
            "ON vd.code = ticket.voucher_code\n" +
            "GROUP BY YEAR(ticket.issued_date),QUARTER(issued_date); ",nativeQuery = true)
    List<QuarterlyIncomeDTO> getQuarterIncome();

    @Query(value = "SELECT YEAR(issued_date) as year,MONTH(issued_date) as month,SUM(fd.fare) - SUM(IFNull(vd.discount_amount,0)) as income FROM ticket \n" +
            "INNER JOIN flight_data fd\n" +
            "ON fd.instance_id = ticket.instance_id\n" +
            "LEFT JOIN voucher_discount vd\n" +
            "ON vd.code = ticket.voucher_code\n" +
            "GROUP BY YEAR(ticket.issued_date) , MONTH(ticket.issued_date); ",nativeQuery = true)
    List<MonthlyIncomeDTO> getMonthlyIncome();

    @Query(value = "SELECT fd.code,YEAR(fd.flight_date) as year,QUARTER(fd.flight_date) as quarter,count(ticket.id) as ticket_count FROM ticket\n" +
            "INNER JOIN flight_data fd\n" +
            "ON ticket.instance_id =  fd.instance_id\n" +
            "GROUP BY fd.code,YEAR(fd.flight_date),QUARTER(fd.flight_date)\n" +
            "ORDER BY YEAR(fd.flight_date),QUARTER(fd.flight_date),COUNT(*) DESC",nativeQuery = true)
    List<QuarterlyTicketCountDTO> getTicketCount();

    @Query(value = "select username,ticket.id as ticket_id,",nativeQuery = true)
    TicketDTO getTicketDTO();
}
