package co.smokefree.controller;

import co.smokefree.dto.ApiResponse;
import co.smokefree.dto.feedback.FeedbackRequest;
import co.smokefree.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
public class FeedbackController {
        private final FeedbackService feedbackService;

        @PostMapping
        public ResponseEntity<ApiResponse> submitFeedback(
                        @Valid @RequestBody FeedbackRequest request) {
                return ResponseEntity.ok(feedbackService.submitFeedback(request));
        }
}