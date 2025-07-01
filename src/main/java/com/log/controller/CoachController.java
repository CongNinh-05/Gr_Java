package com.log.controller;

import jakarta.servlet.http.HttpSession;
import com.log.model.UsersModel;
import com.log.model.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coach")
public class CoachController {

    @GetMapping("/dashboard")
    public String coachDashboard(HttpSession session, Model model) {
        UsersModel user = (UsersModel) session.getAttribute("user");
        if (user == null || user.getRole() != Role.coach) {
            return "redirect:/account";
        }
        model.addAttribute("user", user);
        return "coach/dashboard";
    }
}