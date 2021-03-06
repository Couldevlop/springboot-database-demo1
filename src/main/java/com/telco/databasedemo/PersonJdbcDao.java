package com.telco.databasedemo;

import com.telco.databsedemo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class PersonJdbcDao {


    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<Person> findAll()
    {
      return  jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<Person>(Person.class));
    }


    public Person findById(int id)
    {
        return  jdbcTemplate.queryForObject("SELECT * FROM Person WHERE id=?",
                new Object[]{id}, new BeanPropertyRowMapper<Person>(Person.class));
    }


    public int deleteById(int id)
    {
        return  jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }



    public int insert(Person person)
    {
        return jdbcTemplate.update("INSERT INTO PERSON (ID, NAME, LOCATION, BIRTH_DATE )\n" +
                "VALUES(?,  ?, ?,?);",
                new Object[]{person.getId(), person.getName(), person.getLocation(),
                        new Date(person.getBirthdate().getTime())});
    }



    public int update(Person person)
    {
        return jdbcTemplate.update("UPDATE  PERSON SET  NAME=?, LOCATION=?, BIRTH_DATE=? WHERE id=?, \n" +
                Arrays.toString(new Object[]{person.getName(), person.getLocation(),
                        new Date(person.getBirthdate().getTime()), person.getId()}));
    }
}
