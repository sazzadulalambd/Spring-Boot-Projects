package com.example.authorization.login.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ch.qos.logback.classic.Logger;
import com.example.authorization.login.domain.model.SignupForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.example.authorization.login.domain.model.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.example.authorization.login.domain.service.UserService;

@Controller
public class DashboardController {
    private final UserService userService;

    @Autowired
    public DashboardController(UserService userService) {
        this.userService = userService;
    }

    // Variable for marriage status radio button
    private Map<String, String> radioMarriage;
    private Map<String, String> radioRole;

    // Radio button initialization method (same as user registration screen).
    private Map<String, String> initRadioMarriage() {
        // Implementation to initialize radio buttons for marriage status
        // For example:
        Map<String, String> radioMarriage = new LinkedHashMap<>();
        radioMarriage.put("Married", "true");
        radioMarriage.put("Unmarried", "false");
        return radioMarriage;
    }

    private Map<String, String> initRadioRole() {
        // Implementation to initialize radio buttons for Role status
        // For example:
        Map<String, String> radioRole = new LinkedHashMap<>();
        radioRole.put("General", "ROLE_GENERAL");
        radioRole.put("Admin", "ROLE_ADMIN");
        return radioRole;
    }

    // Method for GET request of home screen
    @GetMapping("/dashboard")
    public String getHome(Model model) {
        // Register a character string to display the user details in the content part
        model.addAttribute("contents", "login/home :: home_contents");
        return "login/homeLayout";
    }

    @GetMapping("/userAdd")
    public String getUserAdd(@ModelAttribute SignupForm form, Model model) {
        // Initialize radio button options
        radioMarriage = initRadioMarriage();
        radioRole = initRadioRole();
        model.addAttribute("contents", "login/userAdd :: userAdd_contents");
        // Add SignupForm and radioMarriage to the model
        model.addAttribute("signupForm", form);
        model.addAttribute("radioMarriage", radioMarriage);
        model.addAttribute("radioRole", radioRole);
        // Return the view name for the signup form template
        return "login/homeLayout";
    }

    // Method for POST request to add a new user
    @PostMapping("/userAdd")
    public String postUserAdd(@ModelAttribute @Valid SignupForm form, BindingResult bindingResult, Model model) {
        // If form validation fails, return to signup form
        if (bindingResult.hasErrors()) {
            return getUserAdd(form, model);
        }
        // If form is valid, print form data and redirect to login page
        System.out.println(form);

        // Variable for insert
        User user = new User();
        user.setUserId(form.getUserId());
        user.setPassword(form.getPassword());
        user.setUserName(form.getUserName());
        user.setBirthday(form.getBirthday());
        user.setAge(form.getAge());
        user.setMarriage(form.isMarriage());
        user.setRole(form.getRole());

        // User registration process
        boolean result = userService.insert(user);

        // Judgment of user registration result
        if (result) {
            System.out.println("Insert success");
        } else {
            System.out.println("Insert failed");
        }
        return "redirect:/userList";
    }

    // Method for GET request of user list screen
    @GetMapping("/userList")
    public String getUserList(Model model) {
        // Register a character string to display the user list in the content part
        model.addAttribute("contents", "login/userList :: userList_contents");
        // Search user list
        List<User> userList = userService.selectMany();
        // Register user list in Model
        model.addAttribute("userList", userList);
        // Get number of data
        int count = userService.count();
        model.addAttribute("userListCount", count);
        return "login/homeLayout";
    }

    // Method for GET request of user details screen
    @GetMapping("/userDetail/{id:.+}")
    public String getUserDetail(@ModelAttribute SignupForm form, Model model, @PathVariable("id") String userId) {
        System.out.println("userId = " + userId);

        model.addAttribute("contents", "login/userDetail :: userDetail_contents");

        Map<String, String> radioMarriage = initRadioMarriage();
        model.addAttribute("radioMarriage", radioMarriage);

        if (userId != null && !userId.isEmpty()) {
            try {
                User user = userService.selectOne(userId);

                form.setUserId(user.getUserId());
                form.setUserName(user.getUserName());
                form.setPassword(user.getPassword());
                form.setBirthday(user.getBirthday());
                form.setAge(user.getAge());
                form.setMarriage(user.isMarriage());

                model.addAttribute("signupForm", form);
            } catch (DataAccessException e) {
                System.out.println("Error retrieving user details" + e);
                model.addAttribute("errorMessage", "Error retrieving user details");
            }
        }

        return "login/homeLayout";
    }

    // Method for POST request to update user details
    @PostMapping(value = "/userDetail", params = "update")
    public String postUserDetailUpdate(@ModelAttribute SignupForm form, Model model) {
        System.out.println("Update button processing");

        try {
            // Create User instance
            User user = new User();

            // Convert form class to User class
            user.setUserId(form.getUserId());
            user.setPassword(form.getPassword());
            user.setUserName(form.getUserName());
            user.setBirthday(form.getBirthday());
            user.setAge(form.getAge());
            user.setMarriage(form.isMarriage());

            // Update execution
            boolean result = userService.updateOne(user);

            if (result) {
                model.addAttribute("result", "Update success");
            } else {
                model.addAttribute("result", "Update failed");
            }
        } catch (DataAccessException e) {
            System.out.println("Error updating user details" + e);
            model.addAttribute("result", "Update failed due to an error");
        }

        // Display user list screen
        return getUserList(model);
    }

    // Method for POST request to delete a user
    @PostMapping(value = "/userDetail", params = "delete")
    public String postUserDetailDelete(@ModelAttribute SignupForm form, Model model) {
        System.out.println("Delete button processing");
        // Delete
        boolean result = userService.deleteOne(form.getUserId());

        if (result) {
            model.addAttribute("result", "Delete success");
        } else {
            model.addAttribute("result", "Delete failed");
        }

        // Display user list screen
        return getUserList(model);
    }

    // Method for POST request to logout
    @PostMapping("/logout")
    public String postLogout() {
        // Redirect to login screen
        return "redirect:/login";
    }

    // Method for GET request to output user list as CSV
    @GetMapping("/userList/csv")
    public ResponseEntity<byte[]> getUserListCsv() {
        userService.userCsvOut();
        byte[] bytes = null;
        try {
            bytes = userService.getFile("sample.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "text/csv; charset=UTF-8");
        header.setContentDispositionFormData("filename", "sample.csv");

        return new ResponseEntity<>(bytes, header, HttpStatus.OK);
    }
}
