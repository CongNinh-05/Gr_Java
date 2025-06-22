package co.smokefree.dto.badge;

import lombok.Data;

@Data
public class BadgeResponse {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
}