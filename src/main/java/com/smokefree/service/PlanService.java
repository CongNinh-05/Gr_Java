package com.smokefree.service;

import com.smokefree.dto.PlanDTO;
import com.smokefree.entity.Plan;
import com.smokefree.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    public Plan createPlan(PlanDTO planDTO) {
        Plan plan = new Plan(
                planDTO.getUserId(),
                planDTO.getTitle(),
                planDTO.getGoal(),
                planDTO.getStartDate(),
                planDTO.getEndDate(),
                planDTO.getIsSample()
        );
        return planRepository.save(plan);
    }

    public List<Plan> getPlansByUserId(Integer userId) {
        return planRepository.findByUserId(userId);
    }
}