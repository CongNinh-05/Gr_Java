package co.smokefree.service;

import co.smokefree.dto.leaderboard.LeaderboardEntry;
import co.smokefree.repository.ProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LeaderboardService {
    List<LeaderboardEntry> getLeaderboard();
}

@Service
@RequiredArgsConstructor
class LeaderboardServiceImpl implements LeaderboardService {
    private final ProgressRepository progressRepository;

    @Override
    public List<LeaderboardEntry> getLeaderboard() {
        return progressRepository.calculateLeaderboard();
    }
}