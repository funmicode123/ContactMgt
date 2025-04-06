package com.funmi.utils;

import com.funmi.DTO.requests.AddContactRequest;
import com.funmi.DTO.requests.CreateSignupRequest;
import com.funmi.DTO.requests.EditContactRequest;
import com.funmi.DTO.requests.LoginRequest;
import com.funmi.DTO.response.EditContactResponse;
import com.funmi.DTO.response.SearchContactResponse;
import com.funmi.data.models.Contact;
import com.funmi.data.repositories.ContactRepository;
import com.funmi.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.funmi.data.models.User;
import java.util.Optional;

@Component
public class Mapper {
    private final ContactRepository contactRepository;
    @Autowired
    private UserRepository userRepository;

    public Mapper(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    public Contact mapRequestContact(AddContactRequest request){
        Contact contact = new Contact();
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhoneNumber(request.getPhoneNumber());
        contact.setLocation(request.getLocation());
        return contact;
    }

    public EditContactResponse mapToResponse(Contact contact) {
        EditContactResponse response = new EditContactResponse();
        response.setFirstName(contact.getFirstName());
        response.setLastName(contact.getLastName());
        response.setEmail(contact.getEmail());
        response.setPhone(contact.getPhoneNumber());
        response.setLocation(contact.getLocation());
        response.setSuccess(true);
        response.setMessage("Contact updated successfully");

        System.out.println("Mapped Response: " + response);

        return response;
    }

    public SearchContactResponse mapToSearchResponse(Contact contact) {
        SearchContactResponse response = new SearchContactResponse();
        response.setFirstName(contact.getFirstName());
        response.setLastName(contact.getLastName());
        response.setEmail(contact.getEmail());
        response.setPhoneNumber(contact.getPhoneNumber());
        response.setLocation(contact.getLocation());
        return response;
    }

    public User mapUserRequest(CreateSignupRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }

    public User mapLoginRequest(LoginRequest request) {
        Optional<User> savedUser= userRepository.findByEmail(request.getEmail());
        return savedUser.isPresent()? savedUser.get():null;
    }
}
