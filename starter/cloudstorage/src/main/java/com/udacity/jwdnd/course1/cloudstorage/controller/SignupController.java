package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


// program flows from html output window to the the controller

// create , update , delete --- Signup details

@Controller()
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String C_SignupPageView(@ModelAttribute("user") User user, Model model) {
        return "signup";
    }

    @PostMapping()
    public String C_SignupCreateUser(@ModelAttribute("user") User user, Model model, RedirectAttributes redirectAttributes) {
        String signupError = null;

        if (!userService.isUsernameAvailable(user.getUserName())) {
            signupError = "The username already exists.";
            if (signupError == null) {
                int rowsAdded = userService.createUser(user);
                if (rowsAdded < 0) signupError = "Unknown Error occurred while signing up.";
                else
                {
                    redirectAttributes.addFlashAttribute("SuccessMessage","Signed Up Successfully");
                    return "redirect:/login";
                }
            }
        } else {
            if (signupError == null) {
                int rowsAdded = userService.createUser(user);
//                System.out.println(user);
                if (rowsAdded < 0) signupError = "Unknown Error occurred while signing up.";
                else
                {
                    redirectAttributes.addFlashAttribute("SuccessMessage","Signed Up Successfully");
                    return "redirect:/login";
                }
            }
        }
        model.addAttribute("signupError", signupError);

        if (signupError == null) {
            Boolean bool = true;
            model.addAttribute("success", bool);
        } else model.addAttribute("signupError", signupError);

        return "signup";
    }
}