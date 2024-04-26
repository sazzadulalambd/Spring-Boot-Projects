package com.example.authorization.login.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    // Method for GET request of login screen.
    @GetMapping("/login")
    public String getLogin(Model model) {
        // Screen transition to login.html
        return "login/login";
    }
    // Method for POST request of login screen.
    @PostMapping("/login")
    public String postLogin(Model model) {
        // Transition to home screen
        return "login/login";
    }
}