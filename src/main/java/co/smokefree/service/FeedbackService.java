package co.smokefree.service;

import co.smokefree.dto.ApiResponse;
import co.smokefree.dto.feedback.FeedbackRequest;
import co.smokefree.entity.Feedback;
import co.smokefree.entity.User;
import co.smokefree.exception.AppException;
import co.smokefree.repository.FeedbackRepository;
import co.smokefree.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface FeedbackService {
    ApiResponse submitFeedback(FeedbackRequest request);
}

@Service
@RequiredArgsConstructor
class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ApiResponse submitFeedback(FeedbackRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException("Không tìm thấy người dùng", HttpStatus.NOT_FOUND));

        Feedback feedback = Feedback.builder()
                .user(user)
                .comment(request.getContent())
                .rating(request.getRating())
                .createdAt(LocalDateTime.now())
                .build();

        feedbackRepository.save(feedback);
        return new ApiResponse(true, "Cảm ơn về phản hồi của bạn!");
    }
}