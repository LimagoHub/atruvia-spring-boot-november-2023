package de.atruvia.webapp.persistence;

import de.atruvia.webapp.persistence.entity.PersonEntity;

public interface PersonenCustomRepository {

    void persist(PersonEntity person);
}
