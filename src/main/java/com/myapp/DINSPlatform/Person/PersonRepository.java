package com.myapp.DINSPlatform.Person;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "person", collectionResourceRel = "person")
public interface PersonRepository extends JpaRepository<Person, Long> {
//  @Query("Select p from Person p where p.name like %:sub%")
//  List<Person> findBySubName(String sub);

  List<Person> findByNameContains(String sub);
}
