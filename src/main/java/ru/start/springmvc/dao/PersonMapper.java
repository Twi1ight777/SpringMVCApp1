package ru.start.springmvc.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.start.springmvc.models.Person;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper {
    // Переводит строку результата SQL в объект Person
    @Override
    public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Person person = new Person();
        // Тривиальная вещь, id в таблице равен id в списке -> можно заменить на встроенный RowMapper
        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setEmail(resultSet.getString("email"));
        person.setAge(resultSet.getInt("age"));
        return person;
    }
}
