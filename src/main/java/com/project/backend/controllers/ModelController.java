package com.project.backend.controllers;

import com.project.backend.models.Aircraft;
import com.project.backend.models.Model;
import com.project.backend.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/model")
public class ModelController {
    @Autowired
    private ModelService modelService;
    @GetMapping("getAll")
    public List<Model> getModel(){
        return modelService.getModel();
    }
    @GetMapping("get/{code}")
    public Model getModelByCode(@PathVariable String code){
        return modelService.findModelByCode(code);
    }
    @PostMapping("add")
    public boolean addModel(@RequestBody Model model){
        return modelService.addModel(model.getICAOCode(),model.getName(),
                model.getSeats(),model.getAgent(),model.getSpeed());
    }
    @PutMapping("update/{code}")
    public boolean updateModel(@RequestBody Model model, @PathVariable String code){
        return modelService.updateModel(model.getICAOCode(),model.getName(),
                model.getSeats(),model.getAgent(),model.getSpeed());
    }
    @DeleteMapping("delete/{code}")
    public boolean deleteModel(@PathVariable String code){
        return modelService.deleteModel(code);
    }
}
