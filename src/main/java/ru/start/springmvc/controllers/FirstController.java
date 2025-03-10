package ru.start.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "hello_world";
    }
}
