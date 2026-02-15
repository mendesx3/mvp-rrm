package com.mvp.infrastructure.persistence;

import com.mvp.core.domain.Contact;

public final class ContactPersistenceMapper {
    private ContactPersistenceMapper() {
    }

    public static ContactJpaEntity toEntity(Contact contact) {
        return new ContactJpaEntity(
                contact.getId(),
                contact.getName(),
                contact.getDocument(),
                contact.getEmail(),
                contact.getType()
        );
    }

    public static Contact toDomain(ContactJpaEntity entity) {
        return new Contact(
                entity.getId(),
                entity.getName(),
                entity.getDocument(),
                entity.getEmail(),
                entity.getType()
        );
    }
}
