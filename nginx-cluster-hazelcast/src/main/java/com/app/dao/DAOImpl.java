/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;

/**
 *
 * @author prokopiukd
 */
@ApplicationScoped
public class DAOImpl implements DAO {

    @Resource
    private DataSource ds;

    @Override
    public void create(Person person) {
        try (Connection c = ds.getConnection()) {
            PreparedStatement ps = c.prepareStatement("INSERT INTO persons(name, age) VALUES(?,?)");
            ps.setString(1, person.getName());
            ps.setInt(2, person.getAge());
            ps.execute();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Person> readByName(String name) {
        try (Connection c = ds.getConnection()) {
            List<Person> persons = new ArrayList();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM persons WHERE name=?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nameValue = rs.getString("name");
                int ageValue = rs.getInt("age");
                Person person = new Person();
                person.setAge(ageValue);
                person.setName(nameValue);
                persons.add(person);
            }
            return persons;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Person> readByAge(int age) {
        try (Connection c = ds.getConnection()) {
            List<Person> persons = new ArrayList();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM persons WHERE age=?");
            ps.setInt(1, age);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nameValue = rs.getString("name");
                int ageValue = rs.getInt("age");
                Person person = new Person();
                person.setAge(ageValue);
                person.setName(nameValue);
                persons.add(person);
            }
            return persons;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Person readById(int id) {
        try (Connection c = ds.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM persons WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nameValue = rs.getString("name");
                int ageValue = rs.getInt("age");
                Person person = new Person();
                person.setAge(ageValue);
                person.setName(nameValue);
                return person;
            }
            throw new IllegalStateException("No data found by id=" + id);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void updateAgeByName(String name, int updatedAge) {
        try (Connection c = ds.getConnection()) {
            PreparedStatement ps = c.prepareStatement("UPDATE persons SET age=? WHERE name=?");
            ps.setInt(1, updatedAge);
            ps.setString(2, name);
            ps.execute();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void updateAgeByAge(int age, int updatedAge) {
        try (Connection c = ds.getConnection()) {
            PreparedStatement ps = c.prepareStatement("UPDATE persons SET age=? WHERE age=?");
            ps.setInt(1, age);
            ps.setInt(2, updatedAge);
            ps.execute();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(String name, int age) {
        try (Connection c = ds.getConnection()) {
            PreparedStatement ps = c.prepareStatement("DELETE FROM persons WHERE name=? and age=?");
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.execute();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
