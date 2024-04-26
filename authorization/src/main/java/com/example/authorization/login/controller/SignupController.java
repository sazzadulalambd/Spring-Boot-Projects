package com.example.authorization.login.controller;
import com.example.authorization.login.domain.model.SignupForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class SignupController {
    // Point 1: Implementation of radio buttons
    private Map<String, String> radioMarriage;
    // Radio button initialization method.
    private Map<String, String> initRadioMarrige() {
        Map<String, String> radio = new LinkedHashMap<>();
        // Store married and unmarried in Map
        radio.put("Married", "true");
        radio.put("Unmarried", "false");
        return radio;
    }

    // Point1 : @ModelAttribute
    // Method for GET request of user registration screen.
    @GetMapping("/signup")
    public String getSignUp(@ModelAttribute SignupForm form, Model model)
    {
        // Radio button initialization method call
        radioMarriage = initRadioMarrige();
        // Register Map for radio button in Model
        model.addAttribute("SignupForm", form);
        model.addAttribute("radioMarriage", radioMarriage);

        // Screen transition to signup.html
        return "login/signup";
    }


    // Method for POST request of user registration screen.
    // Point 2: Receive data bind result
    @PostMapping("/signup")
    public String postSignUp(@ModelAttribute SignupForm form,
                             BindingResult bindingResult,
                             Model model) {
        // Point 3: When data binding fails
        // If the input check fails, return to the user registration screen
        if ( bindingResult.hasErrors() ) {
        // Call the method for GET request and return to the user registration screen
            return getSignUp(form, model);
        }
        // Check the contents of the form by displaying it on the console
        System.out.println(form);
        // redirect to login.html
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
