package ru.start.springmvc.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.start.springmvc.dao.BookDAO;
import ru.start.springmvc.dao.PersonDAO;
import ru.start.springmvc.models.Book;
import ru.start.springmvc.models.Person;
import ru.start.springmvc.util.PersonValidator;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        // Получим все книги из DAO и передадим в представление
        return "books/index";
    }
    // Если книга свободна, мы должны показывать выпадающий список людей. Но если у книги есть хозяин, то нужно его показать
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.show(id));

        Optional<Person> bookOwner = bookDAO.getBookOwner(id);
        // Либо в модель помещен ключ owner, если у книги есть владелец. People список из всех людей нашей таблицы
        if (bookOwner.isPresent()) {
            model.addAttribute("owner", bookOwner.get());
        } else {
            model.addAttribute("people", personDAO.index());
        }

        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }
    @PostMapping()
    public String createBook(@ModelAttribute("book")@Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        bookDAO.save(book);
        // Создадим новую книгу в БД и вернемся на страницу со списком книг
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        // Получим конкретную книгу по id из DAO и передадим её в представление для редактирования
        return "books/edit";
    }
    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                               @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDAO.update(id, book);
        // Обновим конкретного человека в БД и вернемся на страницу со списком людей
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        // Удалем конкретную книгу из БД и вернемся на страницу со списком
        return "redirect:/books";
    }
    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookDAO.release(id);
        // Освободим конкретную книгу от хозяина и вернемся на страницу со списком книг
        return "redirect:/books/" + id;
    }
    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
        // У selectedPerson назначено только поле id, остальные понял пустые (null)
        bookDAO.assign(id, selectedPerson);
        // Назначим конкретную книгу определенному человеку и вернемся на страницу со списком книг
        return "redirect:/books/" + id;
    }
}
