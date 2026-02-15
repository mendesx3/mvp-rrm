package com.mvp.core.ports;

import com.mvp.core.domain.Contact;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContactRepository {
    Contact save(Contact contact);
    Optional<Contact> findById(UUID id);
    List<Contact> findAll();
    void delete(UUID id);
}
