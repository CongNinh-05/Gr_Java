package co.smokefree.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Long feedbackId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private User user;

    @Column(name = "comment", nullable = false, length = 2000)
    private String comment;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "feedback_type")
    private String feedbackType;

    @Column(name = "is_public", nullable = false)
    private boolean isPublic;

    @PrePersist
    @PreUpdate
    private void validateRating() {
        if (rating != null && (rating < 1 || rating > 5)) {
            throw new IllegalArgumentException("Đánh giá từ 1 đến 5");
        }
    }
}