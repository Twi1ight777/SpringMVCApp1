package ru.start.springmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.start.springmvc.dao.PersonDAO;
import ru.start.springmvc.models.Person;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PersonDAO personDAO;

    @Autowired
    public AdminController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    @GetMapping()
    public String adminPage(Model model, @ModelAttribute("person") String person) {
        model.addAttribute("persons", personDAO.index());
        return "adminPage";
    }
    @PatchMapping("/add")
    //@ModelAttribute("persons") создает пустой объект класса Person (id)
    public String addPerson(@ModelAttribute("person") Person person) {
        System.out.println(person.getId());
        return "redirect:/people";
    }
}
