package de.atruvia.webapp.domain.service;

import de.atruvia.webapp.domain.model.Person;

public interface BlackListService {

    boolean isBlacklisted(final Person possibleBlacklistedPerson);
}
