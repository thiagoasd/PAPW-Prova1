package com.Teixeira.PAPW.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Teixeira.PAPW.Domain.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
