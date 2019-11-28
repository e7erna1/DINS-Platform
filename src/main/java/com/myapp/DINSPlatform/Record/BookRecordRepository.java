package com.myapp.DINSPlatform.Record;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "bookRecord", collectionResourceRel = "bookRecord")
public interface BookRecordRepository extends JpaRepository<BookRecord, Long> {

  List<BookRecord> findByPersonId(long personId);

  BookRecord findByIdAndPersonId(long id, long personId);

  List<BookRecord> findByPersonIdAndPhoneNumberContains(long personId, String sub);
}
