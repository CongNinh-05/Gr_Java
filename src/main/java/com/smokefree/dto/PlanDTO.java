package com.smokefree.dto;

import java.time.LocalDate;

public class PlanDTO {

    private Integer userId;
    private String title;
    private String goal;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isSample;

    // Getter, Setter
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGoal() { return goal; }
    public void setGoal(String goal) { this.goal = goal; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Boolean getIsSample() { return isSample; }
    public void setIsSample(Boolean isSample) { this.isSample = isSample; }
}