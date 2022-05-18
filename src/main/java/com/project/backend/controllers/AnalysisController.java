package com.project.backend.controllers;

import com.project.backend.services.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/analysis")
public class AnalysisController {

    @Autowired
    private AnalysisService analysisService;

    @GetMapping("/avgmile")
    @ResponseBody
    public ResponseEntity getAVGMile(){
        return ResponseEntity.ok(analysisService.getAVGMile());
    }

    @GetMapping("/promotionusage")
    @ResponseBody
    public ResponseEntity getPromotionUsage(){
        return ResponseEntity.ok(analysisService.getPromotionUsage());
    }

    @GetMapping("/income/annual")
    @ResponseBody
    public ResponseEntity getAnnualIncome(){
        return ResponseEntity.ok(analysisService.getAnnualIncome());
    }

    @GetMapping("/income/quarter")
    @ResponseBody
    public ResponseEntity getQuarterIncome(){
        return ResponseEntity.ok(analysisService.getQuarterIncome());
    }

    @GetMapping("/income/month")
    @ResponseBody
    public ResponseEntity getMonthlyIncome(){
        return ResponseEntity.ok(analysisService.getMonthlyIncome());
    }

    @GetMapping("/user/month")
    @ResponseBody
    public ResponseEntity getMonthlyUser(){
        return ResponseEntity.ok(analysisService.getMonthUser());
    }

    @GetMapping("/user/quarter")
    @ResponseBody
    public ResponseEntity getQuarterUser(){
        return ResponseEntity.ok(analysisService.getQuarterUser());
    }

    @GetMapping("/user/mob")
    @ResponseBody
    public ResponseEntity getMOBUser(){
        return ResponseEntity.ok(analysisService.getMOBUser());
    }


}
