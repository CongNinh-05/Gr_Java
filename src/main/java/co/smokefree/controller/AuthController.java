package co.smokefree.controller;

import co.smokefree.dto.ApiResponse;
import co.smokefree.dto.auth.AuthResponse;
import co.smokefree.dto.auth.LoginRequest;
import co.smokefree.dto.auth.RegisterRequest;
import co.smokefree.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/test")
    public ResponseEntity<ApiResponse> testServer() {
        return ResponseEntity.ok(new ApiResponse(true, "Máy chủ đang chạy!"));
    }
}