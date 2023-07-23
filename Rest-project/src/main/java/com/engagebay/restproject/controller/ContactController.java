package com.engagebay.restproject.controller;

import com.engagebay.restproject.model.Contact;
import com.engagebay.restproject.service.ContactService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/contact-api")
public class ContactController {


    private ContactService contactService;

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/contacts")
    public ResponseEntity<String> insertContact(@RequestBody Contact contact){
        contactService.addContact(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body("Contact Inserted Successfully");
    }

    @PutMapping("/contacts")
    public ResponseEntity<String> updateContact(@RequestBody Contact contact){
        contactService.updateContact(contact);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Contact updated Successfully");
    }

    @DeleteMapping("/contacts/remove-contact/{id}")
    public ResponseEntity<String> updateContact(@PathVariable("id") String contactId){
        contactService.deleteContact(contactId);
        return ResponseEntity.status(HttpStatus.OK).body("Contact deleted Successfully");
    }

    @GetMapping("/contacts/get-by-id/{id}")
    public ResponseEntity<Contact> getById(@PathVariable("id") String contactId){
        return ResponseEntity.status(HttpStatus.OK).body(contactService.getByID(contactId));
    }

    @GetMapping("/contacts")
    @RolesAllowed("USER")
    public ResponseEntity<List<Contact>> getAllContacts(){
        return ResponseEntity.ok().body(contactService.getAllContacts());
    }

    @GetMapping("/contacts/search")
    ResponseEntity<List<Contact>> getBySearchText(@RequestParam("text") String searchtext){
        return ResponseEntity.ok().body(contactService.getBySearchText(searchtext));
    }

//    @GetMapping("/contacts/search/{text}")
//    ResponseEntity<List<Contact>> getByFirstNameIsLikeIgnoreCaseOrLastNameIsLikeIgnoreCase(@PathVariable("text") String searchtext, @PathVariable("text") String text){
//        return ResponseEntity.ok().body(contactService.getByFirstNameIsLikeIgnoreCaseOrLastNameIsLikeIgnoreCase(searchtext,  text));
//    }
}
