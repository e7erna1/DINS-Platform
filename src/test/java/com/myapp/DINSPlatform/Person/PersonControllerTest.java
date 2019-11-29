package com.myapp.DINSPlatform.Person;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @Disabled
  void getAllPersons() {

  }

  @Test
  @Disabled
  void getAllBySubstring() {

  }

  @Test
  @Disabled
  void getPerson() {

  }

  @Test
  @Disabled
  void addPerson() throws Exception {
    Person person = new Person("Oleg");
    mockMvc.perform(post("/person")
        .content(asJsonString(person))
        .contentType(MediaType.APPLICATION_JSON)
    )
        .andExpect(status().isOk());
  }

  @Test
  @Disabled
  void editPerson() {

  }

  @Test
  @Disabled
  void deletePerson() {

  }

  static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}