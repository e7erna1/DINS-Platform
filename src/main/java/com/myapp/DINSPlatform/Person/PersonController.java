package com.myapp.DINSPlatform.Person;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

  @RequestMapping(method = RequestMethod.POST, value = "/person")
  public ResponseEntity<Person> addPerson(@RequestBody Person person) {
    personService.addPerson(person);
    return new ResponseEntity<Person>(person, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/person/{personId}")
  public ResponseEntity<Person> editPerson(@RequestBody Person person,
      @PathVariable long personId) {
    personService.editPerson(person, personId);
    return new ResponseEntity<Person>(person, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/person/{personId}")
  public ResponseEntity<HttpStatus> deletePerson(@PathVariable long personId) {
    personService.deletePerson(personId);
    return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
  }
}
