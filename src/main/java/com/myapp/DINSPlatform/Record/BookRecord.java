package com.myapp.DINSPlatform.Record;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myapp.DINSPlatform.Person.Person;
import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity(name = "bookRecord")
public class BookRecord {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @NotNull
  private String name;
  private String phoneNumber;
  @NotNull
  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "person_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Person person;

  public BookRecord() {
  }

  public BookRecord(String name, String phoneNumber, Person person) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.person = person;
  }

  public BookRecord(long id, String name, String phoneNumber, Person person) {
    this.id = id;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.person = person;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }
}
