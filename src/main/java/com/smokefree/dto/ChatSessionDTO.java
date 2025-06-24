package com.smokefree.dto;

public class ChatSessionDTO {
    private int userId;
    private int coachId;

    // Constructors, getters, setters
    public ChatSessionDTO() {}
    public ChatSessionDTO(int userId, int coachId) {
        this.userId = userId;
        this.coachId = coachId;
    }

    // Getters and setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getCoachId() { return coachId; }
    public void setCoachId(int coachId) { this.coachId = coachId; }
}