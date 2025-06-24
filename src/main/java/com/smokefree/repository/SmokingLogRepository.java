package com.smokefree.repository;

import com.smokefree.entity.SmokingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface SmokingLogRepository extends JpaRepository<SmokingLog, Integer> {
    @Query("SELECT COUNT(DISTINCT s.date) FROM SmokingLog s WHERE s.userId = :userId AND s.date >= :startDate AND s.date <= :endDate AND s.cigarettesSmoked = 0")
    long countDaysWithoutSmoking(@Param("userId") int userId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT COALESCE(SUM(s.cost), 0) FROM SmokingLog s WHERE s.userId = :userId AND s.date >= :startDate AND s.date <= :endDate")
    BigDecimal calculateSavedMoney(@Param("userId") int userId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}