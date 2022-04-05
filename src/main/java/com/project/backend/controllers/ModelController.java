package com.project.backend.controllers;

import com.project.backend.Util.UtilHelper;
import com.project.backend.models.Model;
import com.project.backend.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/model")
public class ModelController {

    @Autowired
    private ModelService modelService;

    @GetMapping("")
    public List<Model> getAll(){
        return modelService.getAll();
    }

    @GetMapping("/{ICAO}")
    @ResponseBody
    public ResponseEntity getByICAO(@PathVariable String ICAO){
        try{
            Model item = modelService.findByICAO(ICAO);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @PostMapping("")
    public ResponseEntity addModel(@RequestBody Model model){
        try{
            Model item = modelService.addModel(model);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @PutMapping("/{ICAO}")
    public ResponseEntity updateModel(@RequestBody Model model, @PathVariable String ICAO){
        try {
            Model item = modelService.updateModel(model,ICAO);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @DeleteMapping("/{ICAO}")
    public ResponseEntity deleteModel(@PathVariable String ICAO){
        try {
            Model item = modelService.deleteModel(ICAO);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }
}
