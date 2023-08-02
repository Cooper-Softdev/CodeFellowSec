package com.codefellowsec.controllers;

import com.codefellowsec.models.ApplicationUser;
import com.codefellowsec.models.DoPost;
import com.codefellowsec.repositories.UserRepository;
import com.codefellowsec.repositories.PostRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class ApplicationUserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    HttpServletRequest request;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login.html";
    }

    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup.html";
    }

    @GetMapping("/")
    public String getHomePage(Model m, Principal p) {
        if (p != null) {
            String username = p.getName();
            ApplicationUser user = userRepository.findByUsername(username);
            m.addAttribute("username", username);
        }
        return "index.html";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        ResourceNotFoundException(String message) {
            super(message);
        }
    }

    @PostMapping("/signup")
    public RedirectView postSignUp(String username, String password, String firstName, String lastName, LocalDate dateOfBirth, String bio) {
        ApplicationUser user = new ApplicationUser(username, firstName, lastName, dateOfBirth, bio);
        String encryptedPassword = passwordEncoder.encode(password);
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        authWithHttpServletRequest(username, password);
        return new RedirectView("/");
    }

    public void authWithHttpServletRequest(String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            System.out.println("Error while logging in");
            e.printStackTrace();
        }
    }

    @GetMapping("/myprofile")
    public String getProfileInfo(Model m, Principal p) {
        if (p != null) {
            String username = p.getName();
            ApplicationUser currentUser = userRepository.findByUsername(username);
            m.addAttribute("currentUser", currentUser);
            m.addAttribute("username", username);
        }
        return "myprofile.html";
    }

    @PostMapping("/myprofile")
    public RedirectView addPostFromForm(Principal p, String body) {
        if (p != null) {
            String username = p.getName();
            ApplicationUser currentUser = userRepository.findByUsername(username);
            LocalDateTime time = LocalDateTime.now();
            DoPost newPost = new DoPost(body, time, currentUser);
            postRepository.save(newPost);
            return new RedirectView("/myprofile");
        }
        return new RedirectView("/");
    }

    @GetMapping("/users/{id}")
    public String getUserInfo(Model m, Principal p, @PathVariable Long id) {
        if (p != null) {
            String username = p.getName();
            m.addAttribute("username", username);
        }

        ApplicationUser foundUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User ID not found: " + id));
        m.addAttribute("foundUser", foundUser);

        return "users.html";
    }
}
