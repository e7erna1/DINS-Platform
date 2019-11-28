package com.myapp.DINSPlatform.Person;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  private final PersonRepository personRepository;

  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  List<Person> getAllPersons() {
    return personRepository.findAll();
  }

  List<Person> getAllBySubstring(String name) {
    return personRepository.findByNameContains(name);
  }

  Person getPerson(long personId) {
    return personRepository.findById(personId).orElse(null);
  }

  void addPerson(Person person) {
    personRepository.save(person);
  }

  void editPerson(Person person, long personId) {
    personRepository.findById(personId).map(person1 -> {
      person1.setName(person.getName());
      return personRepository.save(person1);
    });
  }

  void deletePerson(long personId) {
    personRepository.findById(personId).map(person1 -> {
      personRepository.delete(person1);
      return null;
    });
  }
}