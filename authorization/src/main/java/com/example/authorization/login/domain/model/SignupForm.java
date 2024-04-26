package com.example.authorization.login.domain.model;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
@Data
public class SignupForm {
    private String userId;
    private String password;
    private String userName;
    // Point : @DateTimeFormat
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date birthday;
    private int age;
    private boolean marriage;
}