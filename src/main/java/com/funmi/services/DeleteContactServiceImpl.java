package com.funmi.services;

import com.funmi.DTO.requests.DeleteContactRequest;
import com.funmi.DTO.response.DeleteContactResponse;
import com.funmi.data.models.Contact;
import com.funmi.data.repositories.ContactRepository;
import com.funmi.exceptions.ContactNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteContactServiceImpl implements DeleteContactService {
    @Autowired
    private ContactRepository contactRepository;

    public DeleteContactResponse deleteContact(String phoneNumber) {
        Optional<Contact> contactOptional = contactRepository.findByPhoneNumber(phoneNumber);

        if (contactOptional.isEmpty()) {
            throw new ContactNotFoundException("Contact with phone number " + phoneNumber + " not found.");
        }

        contactRepository.delete(contactOptional.get());

        DeleteContactResponse response = new DeleteContactResponse();
        response.setSuccess(true);
        response.setMessage("Contact deleted successfully");
        return response;
    }

}
