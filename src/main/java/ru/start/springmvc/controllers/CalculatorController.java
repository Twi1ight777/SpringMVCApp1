package ru.start.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @GetMapping("/first/calculator")
    public String calculate(
            @RequestParam("a") int a,
            @RequestParam("b") int b,
            @RequestParam("action") String action,
            Model model) {

        double result = 0;
        String operation = "";

        switch (action.toLowerCase()) {
            case "addition":
                result = a + b;
                operation = "+";
                break;
            case "subtraction":
                result = a - b;
                operation = "-";
                break;
            case "multiplication":
                result = a * b;
                operation = "*";
                break;
            case "division":
                if (b != 0) {
                    result = (double) a / b;
                    operation = "/";
                } else {
                    model.addAttribute("error", "Деление на ноль запрещено!");
                    return "first/calculator";
                }
                break;
            default:
                model.addAttribute("error", "Неизвестная операция: " + action);
                return "first/calculator";
        }

        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("operation", operation);
        model.addAttribute("result", result);
        return "first/calculator";
    }
}
