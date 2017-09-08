/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.service;

import com.app.dao.DAO;
import com.app.dao.Person;
import java.util.List;
import javax.cache.annotation.CacheDefaults;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author prokopiukd
 */
@CacheDefaults(cacheName = "persons")
@ApplicationScoped
public class PersonServiceImpl implements PersonService {

    @Inject
    private DAO dao;

    @Override
    public void create(Person person) {
        dao.create(person);
    }

    @Override
    public List<Person> readByName(String name) {
        return dao.readByName(name);
    }

    @Override
    public List<Person> readByAge(int age) {
        return dao.readByAge(age);
    }

    @Override
    public Person readById(int id) {
        return dao.readById(id);
    }

    @Override
    public void updateAgeByName(String name, int updatedAge) {
        dao.updateAgeByName(name, updatedAge);
    }

    @Override
    public void updateAgeByAge(int age, int updatedAge) {
        dao.updateAgeByAge(age, updatedAge);
    }

    @Override
    public void delete(String name, int age) {
        dao.delete(name, age);
    }

}
