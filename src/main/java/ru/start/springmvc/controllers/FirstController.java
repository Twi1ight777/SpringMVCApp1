package ru.start.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/first") // Controller-specific prefix for URLs: /first/*
public class FirstController {
    @GetMapping("/hello")
    public String helloWorld() {
        return "first/hello"; // -> /WEB-INF/views/first/hello.html
    }

    @GetMapping("/goodbye")
    public String goodbyeWorld() {
        return "first/goodbye"; // -> /WEB-INF/views/first/goodbye.html
    }
}
