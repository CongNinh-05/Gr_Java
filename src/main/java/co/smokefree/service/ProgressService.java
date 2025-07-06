package co.smokefree.service;

import co.smokefree.dto.progress.ProgressRequest;
import co.smokefree.dto.progress.ProgressResponse;
import co.smokefree.entity.Progress;
import co.smokefree.entity.User;
import co.smokefree.exception.AppException;
import co.smokefree.repository.ProgressRepository;
import co.smokefree.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgressService {
    private final ProgressRepository progressRepository;
    private final UserRepository userRepository;

    public ProgressResponse saveProgress(ProgressRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException("Không tìm thấy người dùng!", HttpStatus.NOT_FOUND));

        Progress progress = new Progress();
        progress.setUser(user);
        progress.setRecordDate(request.getDate());
        progress.setCigarettesSmoked(request.getCigarettesSmoked());
        progress.setMoneySaved(request.getMoneySaved());
        progress.setHealthImprovementNotes(request.getHealthImprovementNotes());

        Progress savedProgress = progressRepository.save(progress);
        return mapToProgressResponse(savedProgress);
    }

    public List<ProgressResponse> getUserProgress(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException("Không tìm thấy người dùng!", HttpStatus.NOT_FOUND));

        return progressRepository.findByUser(user).stream()
                .map(this::mapToProgressResponse)
                .collect(Collectors.toList());
    }

    private ProgressResponse mapToProgressResponse(Progress progress) {
        ProgressResponse response = new ProgressResponse();
        response.setId(progress.getProgressId());
        response.setDate(progress.getRecordDate());
        response.setCigarettesSmoked(progress.getCigarettesSmoked());
        response.setMoneySaved(progress.getMoneySaved());
        response.setHealthImprovementNotes(progress.getHealthImprovementNotes());
        return response;
    }
}