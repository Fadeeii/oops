package com.smartballot.controller;

import com.smartballot.model.User;
import com.smartballot.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // This maps to login.html
    }

  @PostMapping("/login")
public String login(@RequestParam String studentId,
                    @RequestParam String password,
                    Model model) {
    User user = userRepository.findByStudentId(studentId);

    if (user != null && user.getPassword().equals(password)) {
        // Pass user object to dashboard
        model.addAttribute("user", user);
        return "dashboard";
    } else {
        model.addAttribute("error", "Invalid Student ID or Password");
        return "login";
    }
}

}
