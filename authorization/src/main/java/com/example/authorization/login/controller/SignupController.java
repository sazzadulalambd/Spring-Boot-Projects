package com.example.authorization.login.controller;

import com.example.authorization.login.domain.model.SignupForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class SignupController {

    // Initialization of radio buttons for marriage status
    private Map<String, String> radioMarriage;

    // Method to initialize radio button options
    private Map<String, String> initRadioMarriage() {
        Map<String, String> radio = new LinkedHashMap<>();
        // Adding married and unmarried options to the radio button map
        radio.put("Married", "true");
        radio.put("Unmarried", "false");
        return radio;
    }

    // Handler for GET request to display signup form
    @GetMapping("/signup")
    public String getSignUp(@ModelAttribute SignupForm form, Model model) {
        // Initialize radio button options
        radioMarriage = initRadioMarriage();
        // Add SignupForm and radioMarriage to the model
        model.addAttribute("signupForm", form);
        model.addAttribute("radioMarriage", radioMarriage);
        // Return the view name for the signup form template
        return "login/signup";
    }

    // Handler for POST request to submit signup form data
    @PostMapping("/signup")
    public String postSignUp(@ModelAttribute @Valid SignupForm form,
                             BindingResult bindingResult,
                             Model model) {
        // If form validation fails, return to signup form
        if (bindingResult.hasErrors()) {
            return getSignUp(form, model);
        }
        // If form is valid, print form data and redirect to login page
        System.out.println(form);
        return "redirect:/login";
    }
}







//
//    // Method for GET request of user registration screen.
//    @GetMapping("/signup")
//    public String getSignUp(Model model) {
//        // Radio button initialization method call
//        radioMarriage = initRadioMarrige();
//        // Register Map for radio button in Model
//        model.addAttribute("radioMarriage", radioMarriage);
//        // Screen transition to signup.html
//        return "login/signup";
//    }
//    // Method for POST request of user registration screen.
//    @PostMapping("/signup")
//    public String postSignUp(Model model) {
//        // Point 2: Redirect
//        // redirect to login.html
//        return "redirect:/login";
//    }
