package com.smokefree.service;

import com.smokefree.dto.UserDTO;
import com.smokefree.entity.User;
import com.smokefree.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Hàm đăng ký người dùng
    public User registerUser(UserDTO userDTO) {
        // Kiểm tra email đã tồn tại
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email đã tồn tại!");
        }

        // Tạo user mới từ dữ liệu DTO
        User newUser = new User(
                userDTO.getFullName(),
                userDTO.getEmail(),
                passwordEncoder.encode(userDTO.getPassword()), // Mã hóa mật khẩu
                userDTO.getRole()
        );

        // Lưu vào database
        return userRepository.save(newUser);
    }
}