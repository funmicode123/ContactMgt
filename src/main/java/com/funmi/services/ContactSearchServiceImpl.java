package com.funmi.services;

import com.funmi.DTO.response.SearchContactResponse;
import com.funmi.data.models.Contact;
import com.funmi.data.repositories.ContactRepository;
import com.funmi.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ContactSearchServiceImpl implements ContactSearchService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private Mapper mapper;

    public Optional<SearchContactResponse> searchByPhoneNumber(String phoneNumber) {
        return contactRepository.findByPhoneNumber(phoneNumber)
                .map(mapper::mapToSearchResponse);
    }

    public List<Contact> searchByName(String name) {
        return contactRepository.findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(name, name);
    }
}
