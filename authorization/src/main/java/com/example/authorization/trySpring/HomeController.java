package com.example.authorization.trySpring;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/home")
    public String getHome(){
        return "home";
    }

    @PostMapping("/home")
    public String postRequest(@RequestParam("text1") String str, @NotNull Model model) {
        model.addAttribute("sample", str);
        return "homeResponse";
    }


    @PostMapping("/home/db")
    public String postDbRequest(@RequestParam("text2")String str, Model model) {

        try {
            int id = Integer.parseInt(str);
            Employee employee = homeService.findOne(id);

            if (employee == null) {
                return "error";
            }

            model.addAttribute("id", employee.getEmployeeId());
            model.addAttribute("name", employee.getEmployeeName());
            model.addAttribute("age", employee.getEmployeeAge());
            model.addAttribute("department", employee.getEmployeeDepartment());

            return "homeResponseDB";
        } catch (NumberFormatException e) {
            return "error";
        }
    }
}



//// Convert String to int
//int id = Integer.parseInt(str);
//// Search for one record
//Employee employee = homeService.findOne(id);
//// Register search results in Model
//        model.addAttribute("id", employee.getEmployeeId());
//        model.addAttribute("name", employee.getEmployeeName());
//        model.addAttribute("age", employee.getEmployeeAge());
//        model.addAttribute("name", employee.getEmployeeDepartment());
//// Screen transition to helloResponseDB.html
//        return "homeResponseDB";
//