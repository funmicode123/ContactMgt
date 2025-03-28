package com.funmi.services;

import com.funmi.DTO.requests.EditContactRequest;
import com.funmi.DTO.response.EditContactResponse;

public interface EditContactService {
    EditContactResponse editContact(String email, EditContactRequest editContactRequest);
}
