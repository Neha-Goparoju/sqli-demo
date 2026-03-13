package com.neha.sqlidemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Controller
public class LoginController {

    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {

        model.addAttribute("enteredUsername", username);
        model.addAttribute("enteredPassword", password);

        try {

            // Basic SQL Injection detection
            if(username.contains("'") || username.contains("--") || username.toLowerCase().contains("or")){

                model.addAttribute("message", "⚠ SQL Injection Attempt Detected");
                return "login";
            }

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sqli_demo",
                    "root",
                    "root"
            );

            String query =
                    "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                model.addAttribute("message", "Login Successful");

            }else{

                model.addAttribute("message", "Login Failed");

            }

        }
        catch(Exception e){

            e.printStackTrace();
            model.addAttribute("message", "Database Error");

        }

        return "login";
    }
}