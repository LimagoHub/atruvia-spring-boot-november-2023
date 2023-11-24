package de.atruvia.webapp.domain.service;

import de.atruvia.webapp.domain.model.Person;
import de.atruvia.webapp.domain.model.Schwein;

import java.util.Optional;
import java.util.UUID;

public interface SchweineService {

    void erfassen(Schwein schwein) throws SchweineServiceException;

    boolean aendern(Schwein schwein) throws SchweineServiceException;

    boolean loeschen(Schwein schwein) throws SchweineServiceException;

    boolean loeschen(UUID id) throws SchweineServiceException;

    Optional<Schwein> findeNachId(UUID id) throws SchweineServiceException;

   Iterable<Schwein> findeAlle() throws SchweineServiceException;

   boolean fuettern(UUID id) throws SchweineServiceException;
}
