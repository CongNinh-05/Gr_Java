package com.smokefree.service;

import com.smokefree.dto.PlanDTO;
import com.smokefree.entity.Plan;
import com.smokefree.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    private void validatePlan(Plan plan) {
        if (plan.getStartDate() == null) {
            throw new IllegalArgumentException("Ngày bắt đầu là bắt buộc");
        }
        if (plan.getUserId() <= 0) {
            throw new IllegalArgumentException("ID người dùng không hợp lệ");
        }
    }

    public Optional<Plan> findById(int planId) {
        return planRepository.findById(planId);
    }
}