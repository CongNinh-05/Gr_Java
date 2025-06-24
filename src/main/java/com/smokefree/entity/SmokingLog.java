package com.smokefree.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "smoking_logs")
public class SmokingLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int logId;
    private int userId;
    private LocalDate date;
    private int cigarettesSmoked;
    private double cost;

    // Constructors
    public SmokingLog() {}
    public SmokingLog(int userId, LocalDate date, int cigarettesSmoked, double cost) {
        this.userId = userId;
        this.date = date;
        this.cigarettesSmoked = cigarettesSmoked;
        this.cost = cost;
    }

    // Getters and setters
    public int getLogId() { return logId; }
    public void setLogId(int logId) { this.logId = logId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public int getCigarettesSmoked() { return cigarettesSmoked; }
    public void setCigarettesSmoked(int cigarettesSmoked) { this.cigarettesSmoked = cigarettesSmoked; }
    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }
}