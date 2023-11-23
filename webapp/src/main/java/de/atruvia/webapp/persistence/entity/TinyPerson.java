package de.atruvia.webapp.persistence.entity;

import java.util.UUID;

public record TinyPerson(UUID id, String nachname) {

    public TinyPerson(final UUID id, final String nachname) {
        this.id = id;
        this.nachname = nachname;
    }
}
