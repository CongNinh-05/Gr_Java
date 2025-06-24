package co.smokefree.repository;

import co.smokefree.entity.Notification;
import co.smokefree.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUser(User user);

    List<Notification> findByUserAndIsRead(User user, boolean isRead);
}