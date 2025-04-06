package com.funmi.controllers;

import com.funmi.DTO.requests.AddContactRequest;
import com.funmi.DTO.requests.EditContactRequest;
import com.funmi.DTO.response.AddContactResponse;
import com.funmi.DTO.response.DeleteContactResponse;
import com.funmi.DTO.response.EditContactResponse;
import com.funmi.DTO.response.SearchContactResponse;
import com.funmi.data.models.Contact;
import com.funmi.services.ContactSearchService;
import com.funmi.services.ContactService;
import com.funmi.services.DeleteContactService;
import com.funmi.services.EditContactService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    private final ContactService contactService;
    private final EditContactService editContactService;
    private final DeleteContactService deleteContactService;
    private final ContactSearchService contactSearchService;

    public ContactController(ContactService contactService,
                             EditContactService editContactService,
                             DeleteContactService deleteContactService,
                             ContactSearchService contactSearchService) {
        this.contactService = contactService;
        this.editContactService = editContactService;
        this.deleteContactService = deleteContactService;
        this.contactSearchService = contactSearchService;
    }

    @PostMapping("/add")
    public ResponseEntity<AddContactResponse> createContact(@Valid @RequestBody AddContactRequest request) {
        AddContactResponse response = contactService.createContact(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{email}")
    public ResponseEntity<EditContactResponse> editContact(
            @PathVariable("email") String email,
            @Valid  @RequestBody EditContactRequest request) {

        String decodedEmail = URLDecoder.decode(email, StandardCharsets.UTF_8);
        EditContactResponse response = editContactService.editContact(decodedEmail, request);
        return ResponseEntity.ok(response);
    }

//    @PutMapping
//    public ResponseEntity<?> editContact(
//            @RequestParam String email,
//            @Valid @RequestBody EditContactRequest request) {
//        try {
//            System.out.println("Received request to edit contact: " + email);
//            System.out.println("Request body: " + request);
//
//            String decodedEmail = URLDecoder.decode(email, StandardCharsets.UTF_8);
//            System.out.println("Decoded email: " + decodedEmail);
//
//            EditContactResponse response = editContactService.editContact(decodedEmail, request);
//            return ResponseEntity.ok(response);
//
//        } catch (ContactNotFoundException e) {
//            System.out.println("Error: Contact not found - " + e.getMessage());
//            return new ResponseEntity<>(e,HttpStatus.NOT_FOUND);
//
//        } catch (EmailCannotBeChangedException e) {
//            System.out.println("Error: Email cannot be changed - " + e.getMessage());
//            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
//
//        } catch (Exception e) {
//            System.out.println("Unexpected error: " + e.getMessage());
//            e.printStackTrace();
//            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


    @DeleteMapping("/{phoneNumber}")
    public ResponseEntity<DeleteContactResponse> deleteContact(
            @PathVariable
            @Pattern(regexp = "\\+234[-\\s]?([789]\\d{3})[-\\s]?(\\d{3})[-\\s]?(\\d{3})",
                    message = "Invalid phone number") String phoneNumber) {
        DeleteContactResponse response = deleteContactService.deleteContact(phoneNumber);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/phone")
    public ResponseEntity<SearchContactResponse> searchByPhone(@RequestParam String phoneNumber) {
        String decodedNumber = URLDecoder.decode(phoneNumber, StandardCharsets.UTF_8);
        return contactSearchService.searchByPhoneNumber(decodedNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


//    @GetMapping("/search/phone")
//    public ResponseEntity<?> searchByPhone(@RequestParam String phoneNumber) {
//        try {
//            SearchContactResponse response = contactSearchService.searchByPhoneNumber(phoneNumber);
//            return ResponseEntity.ok(response);
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found");
//        }
//    }



    @GetMapping("/search/name")
    public ResponseEntity<List<Contact>> searchByName(@RequestParam String name) {
        List<Contact> contacts = contactSearchService.searchByName(name);
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/show")
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }
}
