package co.smokefree.dto.badge;

import lombok.Data;

@Data
public class BadgeAwardRequest {
    private Long userId;
    private Long badgeId;
}