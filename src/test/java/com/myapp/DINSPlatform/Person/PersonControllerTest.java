package com.myapp.DINSPlatform.Person;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  PersonController personController;

  @Test
  void getAllPersons() throws Exception {
    Person person = new Person("Oleg");

    List<Person> people = Collections.singletonList(person);

    given(personController.getAllPersons()).willReturn(people);

    mockMvc.perform(get("/person")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].name", is(person.getName())));
  }

  @Test
  void getAllBySubstring() throws Exception {
    Person person = new Person("Oleg");
    String sub = "le";
    List<Person> people = Collections.singletonList(person);
    given(personController.getAllBySubstring(sub)).willReturn(people);
    mockMvc.perform(get("/person/name?name=" + sub)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].name", is(person.getName())));
  }

  @Test
  void getPerson() throws Exception {
    Person person = new Person();
    person.setName("Oleg");
    given(personController.getPerson(person.getId())).willReturn(person);
    mockMvc.perform(get("/person/" + person.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("name", is(person.getName())));
  }

  @Test
  void addPerson() throws Exception {
    mockMvc.perform(
        post("/person")
            .content(asJsonString(new Person("Test")))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  void editPerson() throws Exception {
    mockMvc.perform(
        put("/person/{personId}", 1)
            .content(asJsonString(new Person("Test")))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void deletePerson() throws Exception {
    mockMvc.perform(delete("/person/{id}", 1))
        .andExpect(status().isAccepted());
  }

  static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}