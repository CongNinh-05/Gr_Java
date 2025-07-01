package com.log.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.log.model.UsersModel;
import com.log.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/account")
    public String accountPage(Model model) {
        if (!model.containsAttribute("registerRequest")) {
            model.addAttribute("registerRequest", new UsersModel());
        }
        if (!model.containsAttribute("loginRequest")) {
            model.addAttribute("loginRequest", new UsersModel());
        }
        return "account";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel,
                           RedirectAttributes redirectAttributes) {
        UsersModel registeredUser = usersService.registerUser(
                usersModel.getName(),
                usersModel.getEmail(),
                usersModel.getPassword()
        );

        if (registeredUser == null) {
            redirectAttributes.addFlashAttribute("error", "Đăng ký thất bại - Email đã tồn tại");
            redirectAttributes.addFlashAttribute("registerRequest", usersModel);
        } else {
            redirectAttributes.addFlashAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập");
        }
        return "redirect:/account";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, RedirectAttributes redirectAttributes, HttpSession session) {
        UsersModel authenticated = usersService.authenticate(
                usersModel.getEmail(),
                usersModel.getPassword()
        );

        if (authenticated != null) {
            session.setAttribute("user", authenticated);

            // Chuyển hướng theo role
            return switch (authenticated.getRole ()) {
                case admin -> "redirect:/admin/dashboard";
                case coach -> "redirect:/coach/dashboard";
                default -> "redirect:/home";
            };
        } else {
            redirectAttributes.addFlashAttribute("loginError", "Email hoặc mật khẩu không đúng");
            redirectAttributes.addFlashAttribute("loginRequest", usersModel);
            return "redirect:/account";
        }
    }

}