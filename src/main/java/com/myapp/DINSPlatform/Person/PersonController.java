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

  @RequestMapping("/person")
  public List<Person> getAllPersons() {
    return personService.getAllPersons();
  }

  @RequestMapping("/person/name")
  public List<Person> getAllBySubstring(@RequestParam(name = "name") String name) {
    return personService.getAllBySubstring(name);
  }

  @RequestMapping("/person/{personId}")
  public Person getPerson(@PathVariable long personId) {
    return personService.getPerson(personId);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(method = RequestMethod.POST, value = "/person")
  public void addPerson(@RequestBody Person person) {
    personService.addPerson(person);
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(method = RequestMethod.PUT, value = "/person/{personId}")
  public void editPerson(@RequestBody Person person, @PathVariable long personId) {
    personService.editPerson(person, personId);
  }

  @ResponseStatus(HttpStatus.ACCEPTED)
  @RequestMapping(method = RequestMethod.DELETE, value = "/person/{personId}")
  public void deletePerson(@PathVariable long personId) {
    personService.deletePerson(personId);
  }
}
