package com.myapp.DINSPlatform.Person;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class PersonServiceTest {

  @Autowired
  PersonRepository personRepository;

  @BeforeEach
  void init() {
    personRepository.deleteAll();
    personRepository.save(new Person("Name"));
    personRepository.save(new Person("Test"));
  }

  @Test
  void getAllPersons() {
    List<Person> people = new ArrayList<>(personRepository.findAll());
    assertThat(people).hasSize(2);
  }

  @Test
  void getAllBySubstring() {
    List<Person> people = new ArrayList<>(personRepository.findByNameContains("es"));
    assertThat(people).hasSize(1);
  }

  @Test
  void getPerson() {
    List<Person> people = new ArrayList<>(
        Collections.singleton(personRepository.findById((long) 1).orElse(null)));
    assertThat(people).hasSize(1);
  }

  @Test
  void addPerson() {
    personRepository.save(new Person("Mike"));
    List<Person> people = new ArrayList<>(personRepository.findAll());
    assertThat(people).hasSize(3);
  }

  @Test
  void editPerson() {
    Person person = new Person("New Value");
    List<Person> people = new ArrayList<>(personRepository.findAll());
    Person person1 = people.get(1);
    person.setId(person1.getId());
    person1.setName(person.getName());
    personRepository.save(person1);
    assertThat(person1).isEqualTo(personRepository.findById(person.getId()).orElse(null));
  }

  @Test
  void deletePerson() {
    long before = personRepository.count();
    Person person = new Person( "Name");
    personRepository.delete(person);
    assertThat(personRepository.count()).isEqualTo(before - 1);
  }
}