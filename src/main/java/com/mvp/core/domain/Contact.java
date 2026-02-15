package com.mvp.core.domain;

import java.util.Objects;
import java.util.UUID;

public class Contact {
    private final UUID id;
    private final String name;
    private final String document;
    private final String email;
    private final ContactType type;

    public Contact(UUID id, String name, String document, String email, ContactType type) {
        this.id = Objects.requireNonNull(id, "id is required");
        this.name = Objects.requireNonNull(name, "name is required");
        this.document = Objects.requireNonNull(document, "document is required");
        this.email = Objects.requireNonNull(email, "email is required");
        this.type = Objects.requireNonNull(type, "type is required");
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getDocument() { return document; }
    public String getEmail() { return email; }
    public ContactType getType() { return type; }
}
