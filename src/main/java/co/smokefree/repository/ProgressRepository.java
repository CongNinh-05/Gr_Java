package co.smokefree.repository;

import co.smokefree.dto.leaderboard.LeaderboardEntry;
import co.smokefree.entity.Progress;
import co.smokefree.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
    List<Progress> findByUser(User user);

    List<Progress> findByUserAndRecordDate(User user, LocalDate recordDate);

    @Query("""
                SELECT new co.smokefree.dto.leaderboard.LeaderboardEntry(
                    u.userName, COUNT(p.recordDate), SUM(p.moneySaved)
                )
                FROM Progress p
                JOIN p.user u
                WHERE p.cigarettesSmoked = 0
                GROUP BY u.id, u.userName
                ORDER BY COUNT(p.recordDate) DESC, SUM(p.moneySaved) DESC
            """)
    List<LeaderboardEntry> calculateLeaderboard();
}