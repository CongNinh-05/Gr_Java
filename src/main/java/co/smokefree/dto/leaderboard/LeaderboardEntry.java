package co.smokefree.dto.leaderboard;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LeaderboardEntry {
    private String userName;
    private Long smokeFreeDays;
    private Long moneySaved;
}