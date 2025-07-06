package co.smokefree.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "health_progress")
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "progress_id")
    private Long progressId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private User user;

    @Column(name = "record_date", nullable = false)
    private LocalDate recordDate;

    @Column(name = "cigarettes_smoked")
    private Integer cigarettesSmoked;

    @Column(name = "money_saved")
    private Double moneySaved;

    @Column(name = "health_improvement_notes", length = 1000)
    private String healthImprovementNotes;
}