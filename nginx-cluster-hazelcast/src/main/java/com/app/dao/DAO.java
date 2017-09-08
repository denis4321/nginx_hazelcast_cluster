/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dao;

import java.util.List;

/**
 *
 * @author prokopiukd
 */
public interface DAO {

    public void create(Person person);

    public List<Person> readByName(String name);

    public List<Person> readByAge(int age);

    public Person readById(int id);

    public void updateAgeByName(String name, int updatedAge);

    public void updateAgeByAge(int age, int updatedAge);

    public void delete(String name, int age);

}
