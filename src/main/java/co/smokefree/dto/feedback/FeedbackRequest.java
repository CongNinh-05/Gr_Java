package co.smokefree.dto.feedback;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class FeedbackRequest {
    @NotNull
    private Long userId;

    @NotBlank
    @Size(max = 2000)
    private String content;

    @Min(1)
    @Max(5)
    private Integer rating;
}