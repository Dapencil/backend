package com.project.backend.repositories;

import com.project.backend.models.DTO.PopularRouteQuarterlyDTO;
import com.project.backend.models.DTO.QuarterlyTicketCountDTO;
import com.project.backend.models.DTO.TopRouteDTO;
import com.project.backend.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route,String> {

    @Query(value = "SELECT fd.code,YEAR(fd.flight_date) as year,QUARTER(fd.flight_date) as quarter,count(ticket.id) as ticket_count FROM ticket\n" +
            "INNER JOIN flight_data fd\n" +
            "ON fd.instance_id = ticket.instance_id\n" +
            "GROUP BY fd.code,YEAR(fd.flight_date),QUARTER(fd.flight_date)\n" +
            "HAVING YEAR(NOW())= year AND quarter = QUARTER(NOW())\n" +
            "ORDER BY COUNT(*) DESC\n" +
            "LIMIT 5;",nativeQuery = true)
    List<TopRouteDTO> getTopRoute();

    @Query(value = "SELECT fd.code,QUARTER(fd.flight_date) as quarter,COUNT(ticket.id) as ticket_count FROM ticket\n" +
            "INNER JOIN flight_data fd\n" +
            "ON fd.instance_id = ticket.instance_id\n" +
            "GROUP BY fd.code,QUARTER(fd.flight_date)\n" +
            "ORDER BY COUNT(ticket.id) DESC;",nativeQuery = true)
    List<PopularRouteQuarterlyDTO> getPopularRoute();

}
