package com.myapp.DINSPlatform.Record;

import static org.junit.jupiter.api.Assertions.*;

import com.myapp.DINSPlatform.Person.Person;
import com.myapp.DINSPlatform.Person.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class BookRecordServiceTest {

  @Autowired
  BookRecordRepository bookRecordRepository;
  @Autowired
  PersonRepository personRepository;

  @BeforeEach
  void init() {
    bookRecordRepository.deleteAll();
    bookRecordRepository.save();
    bookRecordRepository.save();
  }

  @Test
  void getAllBookRecords() {

  }

  @Test
  void getAllBookRecordsBySubNum() {

  }

  @Test
  void getBookRecord() {

  }

  @Test
  void addBookRecord() {

  }

  @Test
  void editBookRecord() {

  }

  @Test
  void removeBookRecord() {

  }
}