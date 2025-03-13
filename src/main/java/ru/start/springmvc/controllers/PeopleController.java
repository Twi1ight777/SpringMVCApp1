package ru.start.springmvc.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.start.springmvc.dao.PersonDAO;
import ru.start.springmvc.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        // Получим всех людей из DAO и передадим в представление
        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        // Получим конкретного человека по id из DAO и передадим в представление
        return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        // Создадим нового человека и передадим его в представление
        return "people/new";
    }
    @PostMapping()
    public String createPerson(@ModelAttribute("person")@Valid Person person, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        personDAO.save(person);
        // Создадим нового человека в БД и вернемся на страницу со списком людей
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        // Получим конкретного человека по id из DAO и передадим его в представление для редактирования
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        personDAO.update(id, person);
        // Обновим конкретного человека в БД и вернемся на страницу со списком людей
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        // Удалем конкретного человека из БД и вернемся на страницу со списком людей
        return "redirect:/people";
    }
}
