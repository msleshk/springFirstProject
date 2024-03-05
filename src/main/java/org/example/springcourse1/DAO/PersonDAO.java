package org.example.springcourse1.DAO;

import org.example.springcourse1.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> showAll(){
        return jdbcTemplate.query("SELECT * FROM people", new PersonMapper());
    }
    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM people WHERE id=?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }
    public void add(Person person){
        jdbcTemplate.update("INSERT INTO people(name, age) VALUES (?, ?)", person.getName(), person.getAge());
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM people WHERE id=?", id);
    }
    public void update(Person person){
        jdbcTemplate.update("UPDATE people SET name=?, age=? WHERE id=?", person.getName(), person.getAge(), person.getId());
    }
}
