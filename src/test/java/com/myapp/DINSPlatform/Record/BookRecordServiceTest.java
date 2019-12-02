package com.myapp.DINSPlatform.Record;

import static org.assertj.core.api.Assertions.assertThat;

import com.myapp.DINSPlatform.Person.Person;
import com.myapp.DINSPlatform.Person.PersonRepository;
import com.myapp.DINSPlatform.Person.PersonService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(BookRecordService.class)
class BookRecordServiceTest {

  @Autowired
  PersonRepository personRepository;
  @Autowired
  BookRecordRepository bookRecordRepository;
  @Autowired
  TestEntityManager entityManager;
  @Autowired
  BookRecordService bookRecordService;

  @AfterEach
  void destruct() {
    entityManager.clear();
  }

  @Test
  void getAllBookRecords() {
    //Given
    Person person = new Person("Oleg");
    BookRecord bookRecord = new BookRecord("Test", "88005553535", person);
    BookRecord bookRecord1 = new BookRecord("Test2", "88005553636", person);
    entityManager.persist(person);
    entityManager.persist(bookRecord);
    entityManager.persist(bookRecord1);
    entityManager.flush();

    //When
    List<BookRecord> bookRecords = bookRecordService.getAllBookRecords(person.getId());

    //Then
    assertThat(bookRecords)
        .isEqualTo(new ArrayList<BookRecord>(Arrays.asList(bookRecord, bookRecord1)));
  }

  @Test
  void getAllBookRecordsBySubNum() {
    //Given
    Person person = new Person("Oleg");
    BookRecord bookRecord = new BookRecord("Test", "88005553535", person);
    BookRecord bookRecord1 = new BookRecord("Test2", "88005553636", person);
    entityManager.persist(person);
    entityManager.persist(bookRecord);
    entityManager.persist(bookRecord1);
    entityManager.flush();

    //When
    List<BookRecord> bookRecords = bookRecordService
        .getAllBookRecordsBySubNum(person.getId(), "35");

    //Then
    assertThat(bookRecords)
        .isEqualTo(new ArrayList<BookRecord>(Collections.singletonList(bookRecord)));
  }

  @Test
  void getBookRecord() {
    //Given
    Person person = new Person("Oleg");
    BookRecord bookRecord = new BookRecord("Test", "88005553535", person);
    BookRecord bookRecord1 = new BookRecord("Test2", "88005553636", person);
    entityManager.persist(person);
    entityManager.persist(bookRecord);
    entityManager.persist(bookRecord1);
    entityManager.flush();

    //When
    BookRecord bookRecord2 = bookRecordService.getBookRecord(person.getId(), bookRecord.getId());

    //Then
    assertThat(bookRecord2)
        .isEqualTo(new ArrayList<BookRecord>(Collections.singletonList(bookRecord)));
  }

  @Test
  void addBookRecord() {
    //Given
    Person person = new Person("Oleg");
    BookRecord bookRecord = new BookRecord("Test", "88005553535", person);
    BookRecord bookRecord1 = new BookRecord("Test2", "88005553636", person);
    entityManager.persist(person);
    entityManager.persist(bookRecord);
    entityManager.persist(bookRecord1);
    entityManager.flush();

    //When
    BookRecord bookRecord2 = new BookRecord("Test3", "88005553737", person);
    bookRecordService.addBookRecord(bookRecord2, person.getId());

    //Then
    assertThat(bookRecord2)
        .isEqualTo(bookRecordRepository.findById(bookRecord2.getId()).orElse(null));
  }

  @Test
  void editBookRecord() {
    //Given
    Person person = new Person("Oleg");
    BookRecord bookRecord = new BookRecord("Test", "88005553535", person);
    BookRecord bookRecord1 = new BookRecord("Test2", "88005553636", person);
    entityManager.persist(person);
    entityManager.persist(bookRecord);
    entityManager.persist(bookRecord1);
    entityManager.flush();

    //When
    BookRecord bookRecord2 = new BookRecord("Test3", "88005553737", person);
    bookRecord2.setId(bookRecord1.getId());
    bookRecordService.editBookRecord(bookRecord2, person.getId(), bookRecord1.getId());

    //Then
    assertThat(bookRecord2)
        .isEqualTo(bookRecordRepository.findById(bookRecord2.getId()).orElse(null));
  }

  @Test
  void removeBookRecord() {
    //Given
    Person person = new Person("Oleg");
    BookRecord bookRecord = new BookRecord("Test", "88005553535", person);
    BookRecord bookRecord1 = new BookRecord("Test2", "88005553636", person);
    entityManager.persist(person);
    entityManager.persist(bookRecord);
    entityManager.persist(bookRecord1);
    entityManager.flush();

    //When
    bookRecordService.removeBookRecord(person.getId(), bookRecord.getId());

    //Then
    assertThat(bookRecord).isNotEqualTo(bookRecordRepository.findByPersonId(person.getId()));
  }
}