package com.example.authorization.trySpring;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String getHome(){
        return "home";
    }

    @PostMapping("/home")
    public String postRequest(@RequestParam("text1") String str, Model model) {
        model.addAttribute("sample", str);
        return "homeResponse";
    }
}
