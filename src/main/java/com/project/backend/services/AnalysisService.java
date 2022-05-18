package com.project.backend.services;

import com.project.backend.models.DTO.*;
import com.project.backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalysisService {

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private AircraftRepository aircraftRepository;

    public List<AVGMileDTO> getAVGMile(){
        return voucherRepository.getAVGMile();
    }

    public List<PromotionUsageMonthlyDTO> getPromotionUsage(){
        return promotionRepository.getMonthlyUsage();
    }

    public List<AnnualyIncomeDTO> getAnnualIncome(){
        return ticketRepository.getAnnualIncome();
    }

    public List<QuarterlyIncomeDTO> getQuarterIncome(){
        return ticketRepository.getQuarterIncome();
    }

    public List<MonthlyIncomeDTO> getMonthlyIncome(){
        return ticketRepository.getMonthlyIncome();
    }

    public List<MonthlyUserDTO> getMonthUser(){
        return userRepository.getMonthlyUser();
    }

    public List<QuarterlyUserDTO> getQuarterUser(){
        return userRepository.getQuarterUser();
    }

    public List<MOBUserDTO> getMOBUser(){
        return userRepository.getMOBUserCount();
    }

    public List<FrequentPromotionDTO> getFrequentPromotion(){
        return userRepository.getFrequentPromotion();
    }

    public List<FrequentRouteDTO> getFrequentRoute(){
        return userRepository.getFrequentRoute();
    }


}
