package co.smokefree.service;

import co.smokefree.dto.badge.BadgeAwardRequest;
import co.smokefree.dto.badge.BadgeResponse;
import co.smokefree.entity.Badge;
import co.smokefree.entity.User;
import co.smokefree.entity.UserBadge;
import co.smokefree.exception.AppException;
import co.smokefree.repository.BadgeRepository;
import co.smokefree.repository.UserBadgeRepository;
import co.smokefree.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BadgeService {
    private final UserBadgeRepository userBadgeRepository;
    private final UserRepository userRepository;
    private final BadgeRepository badgeRepository;

    public BadgeResponse awardBadge(BadgeAwardRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException("Không tìm thấy người dùng!", HttpStatus.NOT_FOUND));

        Badge badge = badgeRepository.findById(request.getBadgeId())
                .orElseThrow(() -> new AppException("Không tìm thấy huy hiệu!", HttpStatus.NOT_FOUND));

        if (userBadgeRepository.existsByUserAndBadge(user, badge)) {
            throw new AppException("Người dùng đã có huy hiệu này!", HttpStatus.BAD_REQUEST);
        }

        UserBadge userBadge = new UserBadge();
        userBadge.setUser(user);
        userBadge.setBadge(badge);
        userBadge.setEarnedAt(LocalDateTime.now());

        userBadgeRepository.save(userBadge);
        return mapToBadgeResponse(badge);
    }

    public List<BadgeResponse> getUserBadges(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException("Không tìm thấy người dùng!", HttpStatus.NOT_FOUND));

        return userBadgeRepository.findByUser(user).stream()
                .map(userBadge -> mapToBadgeResponse(userBadge.getBadge()))
                .collect(Collectors.toList());
    }

    private BadgeResponse mapToBadgeResponse(Badge badge) {
        BadgeResponse response = new BadgeResponse();
        response.setId(badge.getId());
        response.setName(badge.getName());
        response.setDescription(badge.getDescription());
        response.setImageUrl(badge.getImageUrl());
        return response;
    }
}