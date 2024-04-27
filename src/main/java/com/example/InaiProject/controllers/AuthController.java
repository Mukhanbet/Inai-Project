package com.example.InaiProject.controllers;

import com.example.InaiProject.dto.auth.AuthRegisterRequest;
import com.example.InaiProject.model.MailType;
import com.example.InaiProject.model.User;
import com.example.InaiProject.repository.UserRepository;
import com.example.InaiProject.services.AuthService;
import com.example.InaiProject.services.MailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Properties;

@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final AuthService authService;
    private final MailService mailService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("authRegisterRequest", new AuthRegisterRequest());
        return "register";
    }

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm(Model model) {
        return "forgot-password";
    }

    @PostMapping("/register-new")
    public String registerNewUser(@Valid @ModelAttribute("authRegisterRequest")AuthRegisterRequest authRegisterRequest,
                                  BindingResult result,
                                  Model model) {
        try {
            if(result.hasErrors()) {
//                System.out.println("Result has errors");
                model.addAttribute("authRegisterRequest", authRegisterRequest);
                result.toString();
                return "register";
            }
            if(userRepository.findByEmail(authRegisterRequest.getEmail()).isPresent()) {
//                System.out.println("User has been registered");
                model.addAttribute("authRegisterRequest", authRegisterRequest);
                model.addAttribute("emailError", "Your email has been registered!");
                return "register";
            }
            if(authRegisterRequest.getPassword().equals(authRegisterRequest.getConfirmPassword())) {
//                System.out.println("User successfully saved");
                authService.register(authRegisterRequest);
                model.addAttribute("authRegisterRequest", authRegisterRequest);
                model.addAttribute("success", "User successfully registered");
                Optional<User> user = userRepository.findByEmail(authRegisterRequest.getEmail());
                mailService.sendEmail(user.get(), MailType.REGISTRATION, new Properties());
            } else {
//                System.out.println("Password is not same");
                model.addAttribute("authRegisterRequest", authRegisterRequest);
                model.addAttribute("passwordError", "Password is not same!");
                return "register";
            }

        } catch (Exception e) {
//            e.printStackTrace();
            model.addAttribute("serverError", "Can not registered, error server!");
        }
        return "register";
    }
}
