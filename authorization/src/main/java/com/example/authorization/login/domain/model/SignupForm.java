package com.example.authorization.login.domain.model;

import java.util.Date;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SignupForm {
    // Required input, email address format
    @NotBlank
    @Email
    private String userId;

    // Required input, length 4-16 digits, alphanumeric only

    @Length(min = 4, max = 16)
//    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*(-)_+]+$")
    @NotBlank
    private String password;

    // Required input
    @NotBlank
    private String userName;

    // Required input
    @NotNull
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date birthday;

    // Value is from 20 to 100
    @Min(20)
    @Max(100)
    private int age;

    // Only false is possible
    @AssertFalse
    private boolean marriage;

    @NotBlank
    private String role;

}
