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

  /**
   * Returns all user's {@param personId}  records.
   *
   * @return List of records.
   */
  @RequestMapping("/person/{personId}/phoneBook")
  public List<BookRecord> getAllBookRecords(@PathVariable long personId) {
    return bookRecordService.getAllBookRecords(personId);
  }

  /**
   * Return phone book record by phone number and owner id
   *
   * @param personId - Phone books owner id.
   * @param number - The partial (or full) phone number we use to find matches.
   * @return List of records.
   */
  @RequestMapping("/person/{personId}/phoneBook/number")
  public List<BookRecord> getAllBookRecordsBySubNum(@PathVariable long personId,
      @RequestParam(name = "num") String number) {
    return bookRecordService.getAllBookRecordsBySubNum(personId, number);
  }

  /**
   * Returns phone book record by it's owner and id
   *
   * @param personId - Phone books owner id.
   * @param recordId - Record's id, which we want get.
   * @return A single entry (if found) or null.
   */
  @RequestMapping("/person/{personId}/phoneBook/{recordId}")
  public BookRecord getBookRecord(@PathVariable long personId, @PathVariable long recordId) {
    return bookRecordService.getBookRecord(personId, recordId);
  }

  /**
   * Add phone book record by owner's id
   *
   * @param bookRecord - New instance of {@link BookRecord}, that we want add.
   * @param personId - Owner's id.
   */
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(method = RequestMethod.POST, value = "/person/{personId}/phoneBook")
  public void addBookRecord(@RequestBody BookRecord bookRecord, @PathVariable long personId) {
    bookRecordService.addBookRecord(bookRecord, personId);
  }

  /**
   * Edit phone book record by id and owner's id
   *
   * @param bookRecord - A new instance of {@link BookRecord} with which we want to change the
   * current record.
   * @param personId - Owner's id.
   * @param recordId - Id of the record we will change.
   */
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(method = RequestMethod.PUT, value = "/person/{personId}/phoneBook/{recordId}")
  public void editBookRecord(@RequestBody BookRecord bookRecord, @PathVariable long personId,
      @PathVariable long recordId) {
    bookRecordService.editBookRecord(bookRecord, personId, recordId);
  }

  /**
   * Remove phone book record by id and owner's id
   *
   * @param personId - Owner's id.
   * @param recordId -  Id of the record we will delete.
   */
  @ResponseStatus(HttpStatus.ACCEPTED)
  @RequestMapping(method = RequestMethod.DELETE, value = "/person/{personId}/phoneBook/{recordId}")
  public void removeBookRecord(@PathVariable long personId, @PathVariable long recordId) {
    bookRecordService.removeBookRecord(personId, recordId);
  }

}
