package com.nagarro.controller;


import com.nagarro.model.User;

import com.nagarro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.registerUser(user);
        return "login";
        // return "redirect:/login.jsp?success=registered";  // ✅ Success message for registration
    }

    @PostMapping("/login/user")
    public String userLogin(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        if (userService.validateUser(username, password)) {
            request.getSession().setAttribute("user", username);
            System.out.println("Success"+username);
            return "redirect:/product/dashboard";
            //return "redirect:/user-dashboard.jsp?success=login";  // ✅ Success message for user login
        }
        System.out.println("❌ Login failed for user: " + username);
        return "login";
        // return "redirect:/login.jsp?error=invalid_user";  // ✅ Error message for invalid user login
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Get session if it exists
        if (session != null) {
            session.invalidate(); // Destroy the session
        }
        return "redirect:/login"; // Redirect to login page
    }
}
