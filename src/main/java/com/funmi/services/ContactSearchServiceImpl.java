package com.funmi.services;

import com.funmi.DTO.response.SearchContactResponse;
import com.funmi.data.models.Contact;
import com.funmi.data.repositories.ContactRepository;
import com.funmi.exceptions.ContactNotFoundException;
import com.funmi.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactSearchServiceImpl implements ContactSearchService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private Mapper mapper;

    public SearchContactResponse searchByPhoneNumber(String phoneNumber) {
        return contactRepository.findByPhoneNumber(phoneNumber)
                .map(mapper::mapToSearchResponse)
                .orElse(null);
    }



    public List<Contact> searchByName(String name) {
        return contactRepository.findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(name, name);
    }
}
