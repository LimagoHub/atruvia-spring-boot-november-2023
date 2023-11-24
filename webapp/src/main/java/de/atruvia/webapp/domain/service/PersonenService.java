package de.atruvia.webapp.domain.service;

import de.atruvia.webapp.domain.model.Person;

import java.util.Optional;
import java.util.UUID;

public interface PersonenService {

    void erfassen(Person person) throws PersonenServiceException;

    boolean aendern(Person person) throws PersonenServiceException;

    boolean loeschen(Person person) throws PersonenServiceException;

    boolean loeschen(UUID id) throws PersonenServiceException;

    Optional<Person> findeNachId(UUID id) throws PersonenServiceException;

    ;

    Iterable<Person> findeAlle() throws PersonenServiceException;


}
