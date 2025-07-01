package com.log.controller;

import jakarta.servlet.http.HttpSession;
import com.log.model.UsersModel;
import com.log.model.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String adminDashboard(HttpSession session, Model model) {
        UsersModel user = (UsersModel) session.getAttribute("user");
        if (user == null || user.getRole() != Role.admin) {
            return "redirect:/account";
        }
        model.addAttribute("user", user);
        return "admin/dashboard";
    }
}