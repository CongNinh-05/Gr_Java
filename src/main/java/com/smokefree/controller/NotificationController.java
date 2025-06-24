package com.smokefree.controller;

import com.smokefree.dto.NotificationDTO;
import com.smokefree.entity.Notification;
import com.smokefree.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/notifications")
    public ResponseEntity<Notification> createNotification(@RequestBody NotificationDTO dto) {
        Notification notification = notificationService.createNotification(dto.getUserId(), dto.getContent(), dto.getType());
        return new ResponseEntity<>(notification, HttpStatus.CREATED);
    }

    @GetMapping("/notifications/{userId}")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable int userId) {
        List<Notification> notifications = notificationService.getNotificationsByUserId(userId);
        return ResponseEntity.ok(notifications);
    }
}