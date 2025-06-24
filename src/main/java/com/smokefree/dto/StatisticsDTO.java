package com.smokefree.dto;

import java.math.BigDecimal;

public class StatisticsDTO {
    private long daysWithoutSmoking;
    private BigDecimal savedMoney;

    public StatisticsDTO(long daysWithoutSmoking, BigDecimal savedMoney) {
        this.daysWithoutSmoking = daysWithoutSmoking;
        this.savedMoney = savedMoney;
    }

    // Getters and setters
    public long getDaysWithoutSmoking() { return daysWithoutSmoking; }
    public void setDaysWithoutSmoking(long daysWithoutSmoking) { this.daysWithoutSmoking = daysWithoutSmoking; }
    public BigDecimal getSavedMoney() { return savedMoney; }
    public void setSavedMoney(BigDecimal savedMoney) { this.savedMoney = savedMoney; }
}