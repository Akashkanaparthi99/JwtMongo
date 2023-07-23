package com.engagebay.restproject.service;

import com.engagebay.restproject.model.Contact;

import java.util.List;

public interface ContactService {

    void addContact(Contact contact);
    void updateContact(Contact contact);
    void deleteContact(String contactId);
    Contact getByID(String contactId);

    List<Contact> getAllContacts();

    List<Contact> getBySearchText(String searchtext);

//    List<Contact> getByFirstNameIsLikeIgnoreCaseOrLastNameIsLikeIgnoreCase(String searchtext,  String text);
}
