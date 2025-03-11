package ru.start.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first") // Controller-specific prefix for URLs: /first/*
public class RequestController {
    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname, Model model) {
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");

//        System.out.println("Hello, " + name + " " + surname + "!");
        model.addAttribute("message","Hello, " + name + " " + surname + "!");

        return "first/hello"; // -> /WEB-INF/views/first/hello.html

    }

    @GetMapping("/goodbye")
    public String goodbyePage() {
        return "first/goodbye"; // -> /WEB-INF/views/first/goodbye.html
    }
}
