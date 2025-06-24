package co.smokefree.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "progress")
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "cigarettes_smoked")
    private Integer cigarettesSmoked;

    @Column(name = "money_saved")
    private Double moneySaved;

    @Column(name = "health_improvement_notes", length = 1000)
    private String healthImprovementNotes;
}