package com.log.controller;

import com.log.model.UsersModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        UsersModel user = (UsersModel) session.getAttribute("user");
        if (user == null) {
            return "redirect:/account";
        }
        model.addAttribute("user", user);
        return "home";
    }
}