package com.smokefree.service;

import com.smokefree.dto.StatisticsDTO;
import com.smokefree.entity.Plan;
import com.smokefree.entity.SmokingLog;
import com.smokefree.repository.PlanRepository;
import com.smokefree.repository.SmokingLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class StatisticsService {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private SmokingLogRepository smokingLogRepository;

    public StatisticsDTO getStatistics(int userId) {
        try {
            Optional<Plan> planOptional = planRepository.findByUserIdAndIsSampleFalse(userId);
            if (!planOptional.isPresent()) {
                throw new RuntimeException("Không tìm thấy kế hoạch hoạt động nào cho người dùng " + userId);
            }

            Plan plan = planOptional.get();
            LocalDate startDate = plan.getStartDate();
            LocalDate endDate = plan.getEndDate() != null ? plan.getEndDate() : LocalDate.now();

            long daysWithoutSmoking = smokingLogRepository.countDaysWithoutSmoking(userId, startDate, endDate);
            BigDecimal savedMoney = smokingLogRepository.calculateSavedMoney(userId, startDate, endDate);

            return new StatisticsDTO(daysWithoutSmoking, savedMoney);
        } catch (Exception e) {
            throw new RuntimeException("Có lỗi xảy ra khi lấy thông tin thống kê người dùng " + userId + ": " + e.getMessage(), e);
        }
    }
}