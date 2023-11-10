package com.marjane.Repositories;

import com.marjane.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Person, UUID> {
    Optional<Person> findByPersonalInfoEmail(String email);
    Optional<Person> findByPersonalInfoPhoneNumber(String phoneNumber);
    Optional<Person> findByPersonalInfoPhoneNumberOrPersonalInfoEmail(String phoneNumber, String email);

}