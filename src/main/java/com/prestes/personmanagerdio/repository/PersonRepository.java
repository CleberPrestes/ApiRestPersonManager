package com.prestes.personmanagerdio.repository;


import com.prestes.personmanagerdio.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
