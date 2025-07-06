package co.smokefree.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String password;

    @Column(nullable = false)
    private String userName;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String city;

    @Lob
    private String bio;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserRole role;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "cigarettes_per_day")
    private Integer cigarettesPerDay;

    @Column(name = "cigarette_pack_price")
    private Double cigarettePackPrice;
}