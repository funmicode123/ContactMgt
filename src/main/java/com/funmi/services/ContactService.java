package com.funmi.services;


import com.funmi.DTO.requests.AddContactRequest;
import com.funmi.DTO.response.AddContactResponse;

public interface ContactService {
    AddContactResponse createContact(AddContactRequest request);

}
