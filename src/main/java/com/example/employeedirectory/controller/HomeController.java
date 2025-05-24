package com.example.employeedirectory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "<h1>Welcome to the Employee Directory API</h1><p>Visit <code>/employees</code> to get started.</p>";
    }
}
