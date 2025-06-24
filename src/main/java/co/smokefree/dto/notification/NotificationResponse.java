package co.smokefree.dto.notification;

import lombok.Data;

@Data
public class NotificationResponse {
    private Long id;
    private String message;
    private String sentAt;
    private boolean isRead;
}