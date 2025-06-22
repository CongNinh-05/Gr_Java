package co.smokefree.dto.progress;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ProgressRequest {
    private Long userId;
    private Long planId;
    private LocalDate date;
    private Integer cigarettesSmoked;
    private Double moneySaved;
    private String healthImprovementNotes;
}