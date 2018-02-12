package com.mfm.learn.spring.data.restresource.web;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.mfm.learn.spring.data.restresource.entity.Person;

// 支持spring-data-rest
@RepositoryRestResource(path = "people")
public interface PersonRestRepository extends JpaRepository<Person, Long> {
    @RestResource(path = "nameStartsWith", rel = "nameStartsWith")
    Person findByNameStartsWith(@Param("name") String name);

}
