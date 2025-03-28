package com.funmi.data.repositories;

import com.funmi.data.models.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {
    Optional<Contact> findByEmail(String email);
    Optional<Contact> findByPhoneNumber(String phoneNumber);
    void deleteByPhoneNumber(String phoneNumber);
    List<Contact> findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(String firstName, String lastName);
    List<Contact> findByFirstNameAndLastNameIgnoreCaseContaining(String firstName, String lastName);
//    Optional<Contact> findByFirstName(String firstName);
}
