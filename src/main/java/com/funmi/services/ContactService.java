package com.funmi.services;

import com.funmi.data.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.funmi.data.models.Contact;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(String id) {
        return contactRepository.findById(id);
    }

    public Contact updateContact(String id, Contact contactDetails) {
        return contactRepository.findById(id).map(contact -> {
            contact.setName(contactDetails.getName());
            contact.setEmail(contactDetails.getEmail());
            contact.setPhone(contactDetails.getPhone());
            return contactRepository.save(contact);
        }).orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    public void deleteContact(String id) {
        contactRepository.deleteById(id);
    }
}
