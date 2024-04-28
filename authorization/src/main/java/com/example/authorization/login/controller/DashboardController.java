package com.example.authorization.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.authorization.login.domain.service.UserService;
@Controller
public class DashboardController {
    private final UserService userService;

    @Autowired
    public DashboardController(UserService userService) {
        this.userService = userService;
    }

    // Method for GET request of home screen
    @GetMapping("/dashboard")
    public String getHome(Model model) {
// Register a character string to display the user details in the content part
        model.addAttribute("contents", "login/home :: home_contents");
        return "login/homeLayout";
    }
    // Logout process
    @PostMapping("/logout")
    public String postLogout() {
        // Redirect to log in screen
        return "redirect:/login";
//        return "login/login";
    }
}