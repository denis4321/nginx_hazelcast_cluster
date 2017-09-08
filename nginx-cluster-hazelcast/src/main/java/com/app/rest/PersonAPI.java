/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.rest;

import com.app.dao.Person;
import com.app.service.PersonService;
import com.app.service.PersonServiceImpl;
import java.util.List;
import javax.annotation.PostConstruct;
//import javax.cache.annotation.CacheDefaults;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author prokopiukd
 */
@Path("/")
@ApplicationScoped
public class PersonAPI {

    @Context
    private UriInfo context;
    @Inject
    private PersonService personService;

    @PostConstruct
    public void init() {
        personService = new PersonServiceImpl();
    }

    @GET
    public String test() {
        return "TEST." + personService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Person person) {
        personService.create(person);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(Person person) {
        personService.delete(person.getName(), person.getAge());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/read-by-age/{age}")
    public List<Person> readByAge(@PathParam(value = "age") int age) {
        return personService.readByAge(age);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/read-by-name/{name}")
    public List<Person> readByName(@PathParam(value = "name") String name) {
        return personService.readByName(name);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/read-by-id/{id}")
    public Person readById(@PathParam(value = "id") int id) {
        return personService.readById(id);
    }

    @PUT
    @Path("/update-age-by-age/{age}/{updatedAge}")
    public void updateAgeByAge(@PathParam(value = "age") int age, @PathParam(value = "updatedAge") int updatedAge) {
        personService.updateAgeByAge(age, updatedAge);
    }

    @PUT
    @Path("/update-age-by-name/{age}/{name}")
    public void updateAgeByName(@PathParam(value = "age") int age, @PathParam(value = "name") String name) {
        personService.updateAgeByName(name, age);
    }
}
