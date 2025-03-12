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
        people.add(new Person(++PEOPLE_COUNT,"John Doe", 24, "john.doe@example.com"));
        people.add(new Person(++PEOPLE_COUNT,"Jane Smith", 30, "jane.smith@example.com" ));
        people.add(new Person(++PEOPLE_COUNT,"Bob Johnson", 40, "bob.johnson@example.com" ));
        people.add(new Person(++PEOPLE_COUNT,"Alice Brown", 28, "alice.brown@example.com"  ));
    }

    public List<Person> index() {
        return people;
    }
    public Person show(int id) { // Возвращает человека по идентификатору
        return people.stream().filter(person -> person.getId() == id).findFirst().orElse(null);
    }
    public void save(Person person) { // Сохраняет человека в БД
        if (person.getId() == 0) {
            person.setId(++PEOPLE_COUNT);
            people.add(person);
        } else {
            for (Person p : people) {
                if (p.getId() == person.getId()) {
                    p.setName(person.getName());
                    break;
                }
            }
        }
    }
    public void update(int id, Person updatePerson) {
        Person personToBeUpdated = show(id);
        if (personToBeUpdated!= null) {
            personToBeUpdated.setName(updatePerson.getName());
            personToBeUpdated.setAge(updatePerson.getAge());
            personToBeUpdated.setEmail(updatePerson.getEmail());
        }
    }
    public void delete(int id) { // Удаляет человека из БД
        people.removeIf(person -> person.getId() == id);
    }
}