package com.smokefree.repository;

import com.smokefree.entity.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatSessionRepository extends JpaRepository<ChatSession, Integer> {
    @Query("SELECT c FROM ChatSession c WHERE c.userId = :userId ORDER BY c.startedAt DESC")
    List<ChatSession> findByUserId(@Param("userId") int userId);
}