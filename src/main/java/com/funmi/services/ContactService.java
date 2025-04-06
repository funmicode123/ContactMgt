package com.funmi.services;


import com.funmi.DTO.requests.AddContactRequest;
import com.funmi.DTO.response.AddContactResponse;
import com.funmi.data.models.Contact;

import java.util.List;

public interface ContactService {
    AddContactResponse createContact(AddContactRequest request);
    List<Contact> getAllContacts();
}
