package com.funmi.services;

import com.funmi.DTO.requests.EditContactRequest;
import com.funmi.DTO.response.EditContactResponse;
import com.funmi.data.models.Contact;
import com.funmi.data.repositories.ContactRepository;
import com.funmi.exceptions.ContactNotFoundException;
import com.funmi.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditContactServiceImpl implements EditContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private Mapper mapper;

    @Override
    public EditContactResponse editContact(String email, EditContactRequest request) {
        return contactRepository.findByEmail(email).map(contact -> {
            editRequest(request, contact);
            Contact updatedContact = contactRepository.save(contact);

            return mapper.mapToResponse(updatedContact);
        }).orElseThrow(() -> new ContactNotFoundException("Contact not found"));
    }


    private static void editRequest(EditContactRequest request, Contact contact) {
        if (request.getFirstName() != null) contact.setFirstName(request.getFirstName());
        if (request.getLastName() != null) contact.setLastName(request.getLastName());
        if (request.getPhoneNumber() != null) contact.setPhoneNumber(request.getPhoneNumber());
        if (request.getLocation() != null) contact.setLocation(request.getLocation());
    }

}
