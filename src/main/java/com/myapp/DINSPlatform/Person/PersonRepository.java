package com.myapp.DINSPlatform.Person;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "person", collectionResourceRel = "person")
public interface PersonRepository extends JpaRepository<Person, Long> {

  List<Person> findByNameContains(String sub);
}
