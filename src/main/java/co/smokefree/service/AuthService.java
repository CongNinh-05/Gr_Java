package co.smokefree.service;

import co.smokefree.dto.auth.AuthResponse;
import co.smokefree.dto.auth.LoginRequest;
import co.smokefree.dto.auth.RegisterRequest;
import co.smokefree.entity.User;
import co.smokefree.exception.AppException;
import co.smokefree.repository.UserRepository;
import co.smokefree.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException("Email đã được sử dụng!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUserName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setCigarettesPerDay(request.getCigarettesPerDay());
        user.setCigarettePackPrice(request.getCigarettePackPrice());
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        String token = jwtTokenUtil.generateToken(user.getEmail());
        return new AuthResponse(token, user.getEmail(), user.getRole());
    }

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException("Không tìm thấy người dùng!", HttpStatus.NOT_FOUND));

        String token = jwtTokenUtil.generateToken(user.getEmail());
        return new AuthResponse(token, user.getEmail(), user.getRole());
    }
}