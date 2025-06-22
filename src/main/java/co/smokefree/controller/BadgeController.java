package co.smokefree.controller;

import co.smokefree.dto.ApiResponse;
import co.smokefree.dto.badge.BadgeAwardRequest;
import co.smokefree.dto.badge.BadgeResponse;
import co.smokefree.service.BadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/badges")
@RequiredArgsConstructor
public class BadgeController {

    private final BadgeService badgeService;

    @PostMapping("/award")
    public ResponseEntity<ApiResponse> awardBadge(@RequestBody BadgeAwardRequest request) {
        badgeService.awardBadge(request);
        return ResponseEntity.ok(new ApiResponse(true, "Huy hiệu đã được trao thành công!"));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<BadgeResponse>> getUserBadges(@PathVariable Long userId) {
        return ResponseEntity.ok(badgeService.getUserBadges(userId));
    }
}