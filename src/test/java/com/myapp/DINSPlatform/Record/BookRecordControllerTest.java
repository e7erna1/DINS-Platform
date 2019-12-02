package com.myapp.DINSPlatform.Record;

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
import com.myapp.DINSPlatform.Person.Person;
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
class BookRecordControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  BookRecordController bookRecordController;

  @Test
  void getAllBookRecords() throws Exception {
    Person person = new Person("Oleg");
    BookRecord bookRecord = new BookRecord("Test", "88005553535", person);

    List<BookRecord> bookRecords = Collections.singletonList(bookRecord);

    given(bookRecordController.getAllBookRecords(person.getId())).willReturn(bookRecords);

    mockMvc.perform(get("/person/" + person.getId() + "/phoneBook")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].name", is(bookRecord.getName())));
  }

  @Test
  void getAllBookRecordsBySubNum() throws Exception {
    Person person = new Person("Oleg");
    BookRecord bookRecord = new BookRecord("Test", "88005553535", person);
    String sub = "35";
    List<BookRecord> bookRecords = Collections.singletonList(bookRecord);
    given(bookRecordController.getAllBookRecordsBySubNum(person.getId(), sub))
        .willReturn(bookRecords);
    mockMvc.perform(get("/person/" + person.getId() + "/phoneBook/number?num=" + sub)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].name", is(bookRecord.getName())));
  }

  @Test
  void getBookRecord() throws Exception {
    Person person = new Person("Oleg");
    BookRecord bookRecord = new BookRecord("Test", "88005553535", person);
    given(bookRecordController.getBookRecord(person.getId(), bookRecord.getId()))
        .willReturn(bookRecord);
    mockMvc.perform(get("/person/" + person.getId() + "/phoneBook/" + bookRecord.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("name", is(bookRecord.getName())));
  }

  @Test
  void addBookRecord() throws Exception {
    Person person = new Person("Oleg");
    mockMvc.perform(
        post("/person/" + person.getId() + "/phoneBook")
            .content(asJsonString(new BookRecord("Test", "88005553535", person)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  void editBookRecord() throws Exception {
    Person person = new Person("Oleg");
    BookRecord bookRecord = new BookRecord("Test", "88005553535", person);
    mockMvc.perform(
        put("/person/" + person.getId() + "/phoneBook/" + bookRecord.getId())
            .content(asJsonString(new BookRecord("New Value", "880000000000", person)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void removeBookRecord() throws Exception {
    mockMvc.perform(delete("/person/1/phoneBook/1"))
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