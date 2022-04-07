package com.project.backend.repositories.ResRepo;

import com.project.backend.models.ResponseModel.AvailableFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableFlightRepo extends JpaRepository<AvailableFlight,Integer> {

}
