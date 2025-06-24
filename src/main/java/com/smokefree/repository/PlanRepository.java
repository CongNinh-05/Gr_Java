package com.smokefree.repository;

import com.smokefree.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Integer> {
    @Query("SELECT p FROM Plan p WHERE p.userId = :userId AND p.isSample = FALSE ORDER BY p.startDate DESC LIMIT 1")
    Optional<Plan> findByUserIdAndIsSampleFalse(@Param("userId") int userId);

    List<Plan> findByUserId(@Param("userId") int userId);
}