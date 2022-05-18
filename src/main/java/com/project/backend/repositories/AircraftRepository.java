package com.project.backend.repositories;

import com.project.backend.models.Aircraft;
import com.project.backend.models.DTO.ResponsibleFlightCountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft,String> {

    @Query(value = "SELECT aircraft.reg_number,QUARTER(fi.flight_date) as quarter,COUNT(*) as responsible_flight_amount FROM aircraft\n" +
            "INNER JOIN flightinstance fi\n" +
            "ON fi.aircraft_reg_num = aircraft.reg_number\n" +
            "WHERE fi.aircraft_reg_num <> 'TBA' AND YEAR(fi.flight_date) = YEAR(NOW())\n" +
            "GROUP BY aircraft.reg_number,QUARTER(fi.flight_date);",nativeQuery = true)
    List<ResponsibleFlightCountDTO> getResponsibleCount();
}
