package com.myapp.DINSPlatform.Record;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookRecordController {

  private final BookRecordService bookRecordService;

  public BookRecordController(
      BookRecordService bookRecordService) {
    this.bookRecordService = bookRecordService;
  }

  @RequestMapping("/person/{personId}/phoneBook")
  public List<BookRecord> getAllBookRecords(@PathVariable long personId) {
    return bookRecordService.getAllBookRecords(personId);
  }

  @RequestMapping("/person/{personId}/phoneBook/number")
  public List<BookRecord> getAllBookRecordsBySubNum(@PathVariable long personId,
      @RequestParam(name = "num") String number) {
    return bookRecordService.getAllBookRecordsBySubNum(personId, number);
  }


  @RequestMapping("/person/{personId}/phoneBook/{recordId}")
  public BookRecord getBookRecord(@PathVariable long personId, @PathVariable long recordId) {
    return bookRecordService.getBookRecord(personId, recordId);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(method = RequestMethod.POST, value = "/person/{personId}/phoneBook")
  public void addBookRecord(@RequestBody BookRecord bookRecord, @PathVariable long personId) {
    bookRecordService.addBookRecord(bookRecord, personId);
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(method = RequestMethod.PUT, value = "/person/{personId}/phoneBook/{recordId}")
  public void editBookRecord(@RequestBody BookRecord bookRecord, @PathVariable long personId, @PathVariable long recordId) {
    bookRecordService.editBookRecord(bookRecord, personId, recordId);
  }

  @ResponseStatus(HttpStatus.ACCEPTED)
  @RequestMapping(method = RequestMethod.DELETE, value = "/person/{personId}/phoneBook/{recordId}")
  public void removeBookRecord(@PathVariable long personId, @PathVariable long recordId) {
    bookRecordService.removeBookRecord(personId, recordId);
  }

}
