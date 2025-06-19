package co.smokefree.dto.auth;

import co.smokefree.entity.UserRole;
import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String email;
    private UserRole role;

    public AuthResponse(String token, String email, UserRole role) {
        this.token = token;
        this.email = email;
        this.role = role;
    }
}