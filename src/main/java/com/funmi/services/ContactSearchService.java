package com.funmi.services;

import com.funmi.DTO.response.SearchContactResponse;
import com.funmi.data.models.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactSearchService {
    Optional<SearchContactResponse> searchByPhoneNumber(String phoneNumber);
    List<Contact> searchByName(String name);
}
