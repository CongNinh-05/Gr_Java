package com.smokefree.service;

import com.smokefree.entity.Notification;
import com.smokefree.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public Notification createNotification(int userId, String content, String type) {
        Notification notification = new Notification(userId, content, type);
        return notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsByUserId(int userId) {
        return notificationRepository.findByUserId(userId);
    }
}