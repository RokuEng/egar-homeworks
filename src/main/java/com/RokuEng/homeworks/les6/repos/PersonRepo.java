package com.RokuEng.homeworks.les6.repos;

import com.RokuEng.homeworks.les6.domain.persistence.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {
}
