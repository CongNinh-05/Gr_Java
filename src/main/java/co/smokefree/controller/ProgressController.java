package co.smokefree.controller;

import co.smokefree.dto.ApiResponse;
import co.smokefree.dto.progress.ProgressRequest;
import co.smokefree.dto.progress.ProgressResponse;
import co.smokefree.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    @PostMapping
    public ResponseEntity<ApiResponse> saveProgress(@RequestBody ProgressRequest request) {
        progressService.saveProgress(request);
        return ResponseEntity.ok(new ApiResponse(true, "Tiến trình đã được lưu thành công!"));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ProgressResponse>> getUserProgress(@PathVariable Long userId) {
        return ResponseEntity.ok(progressService.getUserProgress(userId));
    }
}