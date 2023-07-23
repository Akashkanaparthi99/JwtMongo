package com.engagebay.restproject.service;

import com.engagebay.restproject.model.Contact;
import com.engagebay.restproject.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void addContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public void updateContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public void deleteContact(String contactId) {
        contactRepository.deleteById(contactId);
    }

    @Override
    public Contact getByID(String contactId) {
        return contactRepository.findById(contactId).get();
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public List<Contact> getBySearchText(String searchtext) {
        return contactRepository.findBySearchText(searchtext);
    }

//    @Override
//    public List<Contact> getByFirstNameIsLikeIgnoreCaseOrLastNameIsLikeIgnoreCase(String searchtext, String text) {
//        return contactRepository.findByFirstNameIsLikeIgnoreCaseOrLastNameIsLikeIgnoreCase(searchtext,  text);
//    }
}
