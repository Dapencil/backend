package com.project.backend.services;

import com.project.backend.models.Aircraft;
import com.project.backend.models.Model;
import com.project.backend.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelService {
    @Autowired
    private ModelRepository repository;
    public List<Model> getModel(){ return repository.findAll(); }
    public Model findModelByCode(String code){ return repository.findById(code).get();}
    public boolean addModel(String icao,String name,Integer seats,String agent,Double speed){
        Model model = new Model();
        model.setICAOCode(icao);
        model.setName(name);
        model.setSeats(seats);
        model.setAgent(agent);
        model.setSpeed(speed);
        return true;
    }
    public boolean updateModel(String icao,String name,Integer seats,String agent,Double speed){
        Optional<Model> model = repository.findById(icao);
        if (model.isEmpty()){return false;}
        model.get().setName(name);
        model.get().setSeats(seats);
        model.get().setAgent(agent);
        model.get().setSpeed(speed);
        return true;
    }
    public boolean deleteModel(String icao){
        Optional<Model> model = repository.findById(icao);
        if (model.isPresent()) {
            repository.delete(model.get());
            return true;
        }
        return false;
    }
}
