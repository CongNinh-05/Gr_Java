package co.smokefree.dto.progress;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ProgressResponse {
    private Long id;
    private LocalDate date;
    private Integer cigarettesSmoked;
    private Double moneySaved;
    private String healthImprovementNotes;
}