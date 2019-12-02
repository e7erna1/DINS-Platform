package com.myapp.DINSPlatform.Person;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@DataJpaTest
@Import(PersonService.class)
class PersonServiceTest {

  @Autowired
  PersonRepository personRepository;
  @Autowired
  TestEntityManager entityManager;
  @Autowired
  PersonService personService;

  @Test
  void getAllPersons() {
    //Given
    Person person = new Person("Oleg");
    Person person1 = new Person("Alex");
    entityManager.persist(person);
    entityManager.persist(person1);
    entityManager.flush();

    //When
    List<Person> people = personService.getAllPersons();

    //Then
    assertThat(people).isEqualTo(new ArrayList<Person>(Arrays.asList(person, person1)));
  }

  @Test
  void getAllBySubstring() {
    //Given
    Person person = new Person("Oleg");
    Person person1 = new Person("Alex");
    entityManager.persist(person);
    entityManager.persist(person1);
    entityManager.flush();

    //When
    List<Person> people = personService.getAllBySubstring("ex");

    //Then
    assertThat(people).isEqualTo(new ArrayList<Person>(Collections.singletonList(person1)));
  }

  @Test
  void getPerson() {
    //Given
    Person person = new Person("Oleg");
    Person person1 = new Person("Alex");
    entityManager.persist(person);
    entityManager.persist(person1);
    entityManager.flush();

    //When
    Person person2 = personService.getPerson(person.getId());

    //Then
    assertThat(person2).isEqualTo(person);
  }

  @Test
  void addPerson() {
    //Given
    Person person = new Person("Oleg");
    Person person1 = new Person("Alex");
    Person person2 = new Person("Test");
    entityManager.persist(person);
    entityManager.persist(person1);
    entityManager.flush();

    //When
    personService.addPerson(person2);

    //Then
    assertThat(person2).isEqualTo(personRepository.findById(person2.getId()).orElse(null));
  }

  @Test
  void editPerson() {
    Person person = new Person("Oleg");
    Person person1 = new Person("Alex");
    entityManager.persist(person);
    entityManager.persist(person1);
    entityManager.flush();

    //When
    Person newPerson = new Person("New value");
    newPerson.setId(person1.getId());
    personService.editPerson(newPerson, person1.getId());

    //Then
    assertThat(newPerson).isEqualTo(personRepository.findById(person1.getId()).orElse(null));
  }

  @Test
  void deletePerson() {
    Person person = new Person("Oleg");
    Person person1 = new Person("Alex");
    entityManager.persist(person);
    entityManager.persist(person1);
    entityManager.flush();

    //When
    personService.deletePerson(person1.getId());

    //Then
    assertThat(person1).isNotEqualTo(personRepository.findById(person1.getId()).orElse(null));
  }
}