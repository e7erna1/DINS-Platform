package com.myapp.DINSPlatform.Person;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

  private final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Returns all user's (phone books owners)
   *
   * @return List of {@link Person}
   */
  @RequestMapping("/person")
  public List<Person> getAllPersons() {
    return personService.getAllPersons();
  }

  /**
   * Returns user by his name (part of name)
   *
   * @return List of {@link Person}
   */
  @RequestMapping("/person/name")
  public List<Person> getAllBySubstring(@RequestParam(name = "name") String name) {
    return personService.getAllBySubstring(name);
  }

  /**
   * Returns user (phone book owner) by his id
   *
   * @param personId - Id of the user we are looking for
   * @return One Person if it exists, or null if none exist
   */
  @RequestMapping("/person/{personId}")
  public Person getPerson(@PathVariable long personId) {
    return personService.getPerson(personId);
  }

  /**
   * Add user (phone book owner)
   *
   * @param person - New {@link Person} instance, which we will add.
   */
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(method = RequestMethod.POST, value = "/person")
  public void addPerson(@RequestBody Person person) {
    personService.addPerson(person);
  }

  /**
   * Edit user (phone book owner)
   *
   * @param person - A new instance of {@link Person} with which we want to replace the current
   * one.
   * @param personId - Id of the current user we want to replace.
   */
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(method = RequestMethod.PUT, value = "/person/{personId}")
  public void editPerson(@RequestBody Person person, @PathVariable long personId) {
    personService.editPerson(person, personId);
  }

  /**
   * Remove user (phone book owner)
   *
   * @param personId - {@link Person} id, which we want delete.
   */
  @ResponseStatus(HttpStatus.ACCEPTED)
  @RequestMapping(method = RequestMethod.DELETE, value = "/person/{personId}")
  public void deletePerson(@PathVariable long personId) {
    personService.deletePerson(personId);
  }
}
