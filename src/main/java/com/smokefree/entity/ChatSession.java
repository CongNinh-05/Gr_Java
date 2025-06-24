package com.smokefree.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ChatSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sessionId;
    private int userId;
    private int coachId;
    private LocalDateTime startedAt;

    // Constructors, getters, setters
    public ChatSession() {}
    public ChatSession(int userId, int coachId) {
        this.userId = userId;
        this.coachId = coachId;
        this.startedAt = LocalDateTime.now();
    }

    // Getters and setters
    public int getSessionId() { return sessionId; }
    public void setSessionId(int sessionId) { this.sessionId = sessionId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getCoachId() { return coachId; }
    public void setCoachId(int coachId) { this.coachId = coachId; }
    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }
}