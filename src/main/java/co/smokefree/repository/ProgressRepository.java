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

    List<Progress> findByUserAndDate(User user, LocalDate date);

    @Query("""
            SELECT new co.smokefree.dto.leaderboard.LeaderboardEntry(
                u.name as userName,
                COUNT(p.date) as smokeFreeDays,
                SUM(p.moneySaved) as moneySaved
            )
            FROM Progress p
            JOIN p.user u
            WHERE p.cigarettesSmoked = 0
            GROUP BY u.id, u.name
            ORDER BY smokeFreeDays DESC, moneySaved DESC
            LIMIT 10
            """)
    List<LeaderboardEntry> calculateLeaderboard();
}