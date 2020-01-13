package com.myapp.DINSPlatform.Record;

import com.myapp.DINSPlatform.Person.PersonRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookRecordService {

  private final PersonRepository personRepository;
  private final BookRecordRepository bookRecordRepository;

  public BookRecordService(BookRecordRepository bookRecordRepository,
      PersonRepository personRepository) {
    this.bookRecordRepository = bookRecordRepository;
    this.personRepository = personRepository;
  }

  List<BookRecord> getAllBookRecords(long personId) {
    return bookRecordRepository.findByPersonId(personId);
  }

  List<BookRecord> getAllBookRecordsBySubNum(long personId, String number) {
    return bookRecordRepository.findByPersonIdAndPhoneNumberContains(personId, number);
  }

  BookRecord getBookRecord(long personId, long recordId) {
    return bookRecordRepository.findByIdAndPersonId(recordId, personId);
  }

  void addBookRecord(BookRecord bookRecord, long personId) {
    personRepository.findById(personId).map(person -> {
      bookRecord.setPerson(person);
      return bookRecordRepository.save(bookRecord);
    });
  }

  void editBookRecord(BookRecord bookRecord, long personId, long recordId) {
    bookRecordRepository.findById(recordId).map(record -> {
      record.setName(bookRecord.getName());
      record.setPhoneNumber(bookRecord.getPhoneNumber());
      return bookRecordRepository.save(record);
    });
  }

  void removeBookRecord(long personId, long recordId) {
    bookRecordRepository.delete(bookRecordRepository.findByIdAndPersonId(recordId, personId));
  }
}