package com.mvp.infrastructure.persistence;

import com.mvp.core.domain.Contact;
import com.mvp.core.ports.ContactRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ContactRepositoryJpaAdapter implements ContactRepository {
    private final SpringDataContactRepository repository;

    public ContactRepositoryJpaAdapter(SpringDataContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public Contact save(Contact contact) {
        ContactJpaEntity saved = repository.save(ContactPersistenceMapper.toEntity(contact));
        return ContactPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Contact> findById(UUID id) {
        return repository.findById(id).map(ContactPersistenceMapper::toDomain);
    }

    @Override
    public List<Contact> findAll() {
        return repository.findAll().stream().map(ContactPersistenceMapper::toDomain).toList();
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
