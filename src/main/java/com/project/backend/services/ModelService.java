package com.project.backend.services;

import com.project.backend.models.Model;
import com.project.backend.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ModelService {

    @Autowired
    private ModelRepository repository;

    public List<Model> getAll(){
        return repository.findAll();
    }

    public Model findByICAO(String ICAO){
        Model item = repository.findById(ICAO)
                .orElseThrow(() -> new NoSuchElementException("Doesn't exist"));
        return item;
    }

    public Model add(Model model){
        try{
            speedValidation(model.getSpeed());
            seatsValidation(model.getSeats());
            model.setSpeed(roundNumber(model.getSpeed()));
            return repository.save(model);
        }catch (Exception e){
            throw e;
        }
    }

    public Model update(Model newItem,String ICAO){
        try{
            Model item = findByICAO(ICAO);
            speedValidation(newItem.getSpeed());
            seatsValidation(newItem.getSeats());
            return repository.save(item);
        }catch (Exception e) {
            throw e;
        }
    }

    public Model delete(String ICAO){
        Model item = findByICAO(ICAO);
        repository.delete(item);
        return item;
    }

    private Double roundNumber(Double number){
        return Math.round(number * 100d) / 100d;
    }

    private void speedValidation(Double speed){
        if(speed<0){
            throw new IllegalArgumentException("speed must be positive");
        }
    }

    private void seatsValidation(Integer seats){
        if(seats<0){
            throw new IllegalArgumentException("seats must be positive");
        }
    }
}
