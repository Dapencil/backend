package com.project.backend.repositories;

import com.project.backend.models.FlightInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightInstanceRepository extends JpaRepository<FlightInstance,Integer> {

    @Query(value = "select * from FlightInstances  where flight_id = ?1 order by instance_id desc limit 1",
            nativeQuery = true)
    FlightInstance getRecent(String flightId);
}
