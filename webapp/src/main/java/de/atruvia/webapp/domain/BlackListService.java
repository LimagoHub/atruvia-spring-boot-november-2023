package de.atruvia.webapp.domain;

public interface BlackListService {

    boolean isBlacklisted(final Person possibleBlacklistedPerson);
}
