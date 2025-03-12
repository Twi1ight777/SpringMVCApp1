package ru.start.springmvc.dao;

import org.springframework.stereotype.Component;
import ru.start.springmvc.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    // Добавлять, удалять и изменять данные людей в БД здесь
    private static int PEOPLE_COUNT = 0;

    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "Vlad087898";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (connection != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Person")) {

            while (resultSet.next()) {
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setAge(resultSet.getInt("age"));

                people.add(person);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return people;
    }
    public Person show(int id) {
        Person person = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM Person WHERE id =?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setAge(resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }
    public void save(Person person) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Person (name, email, age) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getEmail());
            preparedStatement.setInt(3, person.getAge());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(int id, Person updatePerson) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Person SET name =?, age =?, email =? WHERE id =?")) {
            preparedStatement.setString(1, updatePerson.getName());
            preparedStatement.setInt(2, updatePerson.getAge());
            preparedStatement.setString(3, updatePerson.getEmail());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Person WHERE id =?")) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}