package com.smokefree.dto;

public class NotificationDTO {
    private int userId;
    private String content;
    private String type;

    // Constructors, getters, setters
    public NotificationDTO() {}
    public NotificationDTO(int userId, String content, String type) {
        this.userId = userId;
        this.content = content;
        this.type = type;
    }

    // Getters and setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}