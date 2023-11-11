package com.marjane.Repositories;

import com.marjane.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserId(UUID id);
    Optional<User> findByPersonalInfoEmail(String email);
    Optional<User> findByPersonalInfoPhoneNumber(String phoneNumber);
    Optional<User> findByPersonalInfoPhoneNumberOrPersonalInfoEmail(String phoneNumber, String email);

}