package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


// program flows from html output window to the the controller


// create , update , delete --- Credential details


@Controller
@RequestMapping("/credentials")
public class CredentialsController {

    private CredentialsService credentialsService;

    public CredentialsController(CredentialsService credentialsService) {
        this.credentialsService = credentialsService;
    }

    @PostMapping("/save")
    public String C_CredentialsSaveAndUpdate(@ModelAttribute("credentials") Credentials credentials, Model model, Authentication authentication) throws IOException {
        String error = null;
        Integer numberOfCredentials;

        switch (credentials.getCredentialsId()) {
            case 0:
                numberOfCredentials = credentialsService.addUserCredentials(credentials, authentication);
//                System.out.println(credentials);
                if (numberOfCredentials < 0) error = "Error occurred while adding credentials.";
                break;
            default:
                numberOfCredentials = credentialsService.updateUserCredentials(credentials);
//                System.out.println(credentials);
                if (numberOfCredentials < 0) error = "Error occurred while updating credentials.";
                break;
        }
        if (error != null) model.addAttribute("error", error);
        else {
            Boolean bool = true;
            model.addAttribute("success", bool);
        }
        model.addAttribute("activeTab", "credentials");
        return "result";
    }


    @GetMapping("/delete/{credentialsId}")
    public String C_CredentialsDelete(@PathVariable Integer credentialsId, Model model) {
        credentialsService.deleteUserCredentials(credentialsId);
        model.addAttribute("activeTab", "credentials");
        Boolean bool = true;
        model.addAttribute("success", bool);
        return "result";
    }
}
