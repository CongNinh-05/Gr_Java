package co.smokefree.dto.message;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MessageRequest {
    @NotNull
    private Long senderId;

    @NotNull
    private Long receiverId;

    @NotBlank
    @Size(max = 1000)
    private String content;
}