package com.smokefree.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notificationId;
    private int userId;
    private String content;
    private LocalDateTime sentAt;
    private String type;

    // Constructors, getters, setters
    public Notification() {}
    public Notification(int userId, String content, String type) {
        this.userId = userId;
        this.content = content;
        this.type = type;
        this.sentAt = LocalDateTime.now();
    }

    // Getters and setters
    public int getNotificationId() { return notificationId; }
    public void setNotificationId(int notificationId) { this.notificationId = notificationId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}