package co.smokefree.repository;

import co.smokefree.entity.Badge;
import co.smokefree.entity.User;
import co.smokefree.entity.UserBadge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBadgeRepository extends JpaRepository<UserBadge, Long> {
    List<UserBadge> findByUser(User user);

    boolean existsByUserAndBadge(User user, Badge badge);
}