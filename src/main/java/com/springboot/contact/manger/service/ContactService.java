package com.springboot.contact.manger.service;

import com.springboot.contact.manger.model.contact.Contact;
import com.springboot.contact.manger.repository.ContactRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    public Page<Contact> getAllContacts(int page, int size){
        return contactRepository.findAll(PageRequest.of(page, size, Sort.by("name")));
    }

    public Contact getContact(String id){
        return contactRepository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found !"));
    }

    public Contact createContact(Contact contact){
        return contactRepository.save(contact);
    }

    public void deleteContact(String id){
        contactRepository.deleteById(id);
    }

    public String uploadPhoto(String id, MultipartFile file){
        Contact contact = getContact(id);
        String uploadedUrl =  null;
        contact.setPhotoUrl(uploadedUrl);
        contactRepository.save(contact);
        return uploadedUrl;
    }


}
