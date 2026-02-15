package com.mvp.infrastructure.persistence;

import com.mvp.core.domain.ContactType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "contacts")
public class ContactJpaEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String document;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContactType type;

    protected ContactJpaEntity() {
    }

    public ContactJpaEntity(UUID id, String name, String document, String email, ContactType type) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.email = email;
        this.type = type;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getDocument() { return document; }
    public String getEmail() { return email; }
    public ContactType getType() { return type; }
}
