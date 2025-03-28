package com.funmi.services;

import com.funmi.DTO.requests.AddContactRequest;
import com.funmi.DTO.response.AddContactResponse;
import com.funmi.data.repositories.ContactRepository;
import com.funmi.exceptions.PhoneNumberAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.funmi.data.models.Contact;
import com.funmi.utils.Mapper;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public AddContactResponse createContact(AddContactRequest request) {
        String normalizedPhone = normalizePhoneNumber(request.getPhoneNumber());
        if (contactRepository.findByPhoneNumber(normalizedPhone).isPresent()) {
            throw new PhoneNumberAlreadyExistsException("Phone number already exists: " + normalizedPhone);
        }

        Contact contact = mapper.mapRequestContact(request);
        contactRepository.save(contact);
        AddContactResponse response = new AddContactResponse();
        response.setSuccess(true);
        response.setMessage("Contact created successfully");
        return response;
    }

    private String normalizePhoneNumber(String phoneNumber) {
        return phoneNumber.replaceAll("[^\\d+]", "");
    }


    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(String id) {
        return contactRepository.findById(id);
    }

    public Contact updateContact(String id, Contact contactDetails) {
        return contactRepository.findById(id).map(contact -> {
            contact.setFirstName(contactDetails.getFirstName());
            contact.setLastName(contactDetails.getLastName());
            contact.setEmail(contactDetails.getEmail());
            contact.setPhoneNumber(contactDetails.getPhoneNumber());
            contact.setLocation(contactDetails.getLocation());
            return contactRepository.save(contact);
        }).orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    public void deleteContact(String id) {
        contactRepository.deleteById(id);
    }
}
