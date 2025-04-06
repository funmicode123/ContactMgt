package com.funmi.services;

import com.funmi.DTO.requests.AddContactRequest;
import com.funmi.DTO.requests.EditContactRequest;
import com.funmi.DTO.response.AddContactResponse;
import com.funmi.DTO.response.DeleteContactResponse;
import com.funmi.DTO.response.EditContactResponse;
import com.funmi.DTO.response.SearchContactResponse;
import com.funmi.data.models.Contact;
import com.funmi.data.repositories.ContactRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class ContactServiceImplTest {

    @Autowired
    private ContactService contactService;
    @Autowired
    EditContactService editContactService;
    @Autowired
    DeleteContactService deleteContactService;
    @Autowired
    private ContactSearchService contactSearchService;
    @Autowired
    private ContactRepository contactRepository;

    @Test
    void createContact() {
        AddContactRequest request = new AddContactRequest();
        request.setFirstName("John");
        request.setLastName("David");
        request.setEmail("john.doe@gmail.com");
        request.setPhoneNumber("+2349065775432");
        request.setLocation("Lagos");

        AddContactResponse response = contactService.createContact(request);
        assertNotNull(response);
        assertThat(response.getMessage()).isEqualTo("Contact created successfully");

        Optional<Contact> savedContact = contactRepository.findByEmail(request.getEmail());
        assertTrue(savedContact.isPresent(), "Contact should be present");

        Contact contact = savedContact.get();
        assertThat(contact.getFirstName()).isEqualTo(request.getFirstName());;
    }

    @Test
    void updateContact() {
        AddContactRequest addRequest = new AddContactRequest();
        addRequest.setFirstName("Jane");
        addRequest.setLastName("Doe");
        addRequest.setEmail("jane.doe@gmail.com");
        addRequest.setPhoneNumber("+2348123456789");
        addRequest.setLocation("Abuja");

        contactService.createContact(addRequest);

        EditContactRequest editRequest = new EditContactRequest();
        editRequest.setFirstName("Janet");
        editRequest.setLastName("Doe");
        editRequest.setPhoneNumber("+2348123456789");
        editRequest.setLocation("Lagos");

        EditContactResponse response = editContactService.editContact("jane.doe@gmail.com", editRequest);

        assertNotNull(response);
        assertThat(response.getMessage()).isEqualTo("Contact updated successfully");

        Optional<Contact> updatedContact = contactRepository.findByEmail("jane.doe@gmail.com");
        assertTrue(updatedContact.isPresent(), "Contact should be present");

        Contact contact = updatedContact.get();
        assertThat(contact.getFirstName()).isEqualTo(editRequest.getFirstName());
        assertThat(contact.getLocation()).isEqualTo(editRequest.getLocation());
    }

    @Test
    void deleteContact() {
        Optional<Contact> existingContact = contactRepository.findByPhoneNumber("+2348123456789");
        assertNotNull(existingContact, "Contact should exist");

        DeleteContactResponse response = deleteContactService.deleteContact("+2348123456789");

        assertThat(response.getMessage()).isEqualTo("Contact deleted successfully");
        assertTrue(response.isSuccess(), "Deletion should be successful");

        Optional<Contact> deletedContact = contactRepository.findByPhoneNumber("+2348123456789");
        assertNull(deletedContact, "Contact should be deleted");
    }

    @Test
    void searchByPhoneNumberTest() {

        Optional<Contact> contact = contactRepository.findByPhoneNumber("+2349065775432");

        Optional<SearchContactResponse> foundContact = contactSearchService.searchByPhoneNumber("+2348123456789");
        assertTrue(foundContact.isPresent(), "Contact should exist");
    }


}
