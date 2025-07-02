package co.smokefree.controller;

import co.smokefree.dto.leaderboard.LeaderboardEntry;
import co.smokefree.repository.ProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
@RequiredArgsConstructor
public class LeaderboardController {
    private final ProgressRepository progressRepository;

    @GetMapping
    public ResponseEntity<List<LeaderboardEntry>> getLeaderboard() {
        List<LeaderboardEntry> leaderboard = progressRepository.calculateLeaderboard();
        return ResponseEntity.ok(leaderboard);
    }
}