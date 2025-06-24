package com.smokefree.repository;

import com.smokefree.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    @Query("SELECT n FROM Notification n WHERE n.userId = :userId ORDER BY n.sentAt DESC")
    List<Notification> findByUserId(@Param("userId") int userId);

    @Query("SELECT n FROM Notification n WHERE n.userId = :userId AND n.type = :type ORDER BY n.sentAt DESC")
    List<Notification> findByUserIdAndType(@Param("userId") int userId, @Param("type") String type);
}