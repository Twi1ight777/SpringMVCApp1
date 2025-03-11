package ru.start.springmvc.dao;

import org.springframework.stereotype.Component;
import ru.start.springmvc.models.Person;

import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    // Добавлять, удалять и изменять данные людей в БД здесь
    private static final List<Person> people = new ArrayList<>();
    private static int PEOPLE_COUNT = 0;

    static {
        people.add(new Person(++PEOPLE_COUNT,"John Doe"));
        people.add(new Person(++PEOPLE_COUNT,"Jane Smith"));
        people.add(new Person(++PEOPLE_COUNT,"Bob Johnson"));
        people.add(new Person(++PEOPLE_COUNT,"Alice Brown"));
    }

    public List<Person> index() {
        return people;
    }
    public Person show(int id) { // Возвращает человека по идентификатору
        return people.stream().filter(person -> person.getId() == id).findFirst().orElse(null);
    }
}
