package com.arpit.SmartContractManager.controlller;

import com.arpit.SmartContractManager.dao.UserRepository;
import com.arpit.SmartContractManager.entities.User;
import com.arpit.SmartContractManager.helper.Message;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository ur;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home - Smart Contact Manager");
        return "home";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About - Smart Contact Manager");
        return "about";
    }

    @RequestMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("title", "SignUp - Smart Contact Manager");
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping(value = "/do_register", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result,
                               @RequestParam(value="agreement", defaultValue = "false") boolean agreement,
                               Model model, HttpSession session) {

        try {
            if (!agreement) {
                System.out.println("You've not agreed terms and conditions");
                throw new Exception("Terms and conditions not accepted");
            }

            if(result.hasErrors()) {
                System.out.println("ERROR: "+ result.toString());
                model.addAttribute("user", user);
                return"SignUp";
            }

            user.setRole("ROLE_USER");
            user.setEnabled(true);
            user.setImageUrl("Yuvansh.jpeg");
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            System.out.println("-----------------------------------------------------------------");
            System.out.println("Agreement: " + agreement);
            System.out.println("USER: " + user);

            User res = this.ur.save(user);
            model.addAttribute("user", new User());

            session.setAttribute("message", new Message("Successfully Registered!!!", "alert-success"));
            return "signup";
        }
        catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("user", user);
            session.setAttribute("message", new Message("Something went wrong!!! " + e.getMessage(), "alert-danger"));
            return "signup";
        }


    }

    @RequestMapping("/signin")
    public String customLogin(Model model) {
        model.addAttribute("title", "Login - Smart Contact Manager");
        return "signin";
    }
}
