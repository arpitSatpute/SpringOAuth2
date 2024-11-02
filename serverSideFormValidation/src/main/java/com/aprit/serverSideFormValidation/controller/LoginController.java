package com.aprit.serverSideFormValidation.controller;

import com.aprit.serverSideFormValidation.entities.LoginData;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {

    @GetMapping("/form")
    public String openForm(Model model) {
        model.addAttribute("loginData", new LoginData());
        return "form";
    }

    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute("loginData") LoginData loginData, BindingResult result) {

        if(result.hasErrors()) {
            System.out.println(result);
        }

        System.out.println(loginData);
        return "success";
    }
}
