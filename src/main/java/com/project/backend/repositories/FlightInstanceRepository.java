package com.project.backend.repositories;

import com.project.backend.models.FlightInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightInstanceRepository extends JpaRepository<FlightInstance,Integer> {

    @Query(value = "select * from flightinstance  where flight_id = ?1 order by instance_id desc limit 1",
            nativeQuery = true)
    FlightInstance getRecent(String flightId);

    @Procedure
    Integer getSeats(Integer instanceId);
}
