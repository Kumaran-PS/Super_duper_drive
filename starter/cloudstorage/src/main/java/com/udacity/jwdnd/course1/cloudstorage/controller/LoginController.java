package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


// program flows from html output window to the the controller

// inputs the username and the password to enter into the applcation

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping()
    public String C_loginPageView() {
        return "login";
    }
}
