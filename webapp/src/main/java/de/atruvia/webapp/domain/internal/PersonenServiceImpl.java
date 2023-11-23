package de.atruvia.webapp.domain.internal;

import de.atruvia.webapp.domain.Person;
import de.atruvia.webapp.domain.PersonenMapper;
import de.atruvia.webapp.domain.PersonenService;
import de.atruvia.webapp.domain.PersonenServiceException;
import de.atruvia.webapp.persistence.PersonenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Transactional(propagation = Propagation.REQUIRED, rollbackFor = PersonenServiceException.class, isolation = Isolation.READ_COMMITTED)
@RequiredArgsConstructor
public class PersonenServiceImpl implements PersonenService {


    private final PersonenRepository repo;
    private final PersonenMapper mapper;
    @Qualifier("blacklist")
    private final List<String> blackList;



    @Override
    public void erfassen(final Person person) throws PersonenServiceException {
        try {
            checkPerson(person);
            if (repo.existsById(person.getId())) throw new PersonenServiceException("Person already exists");
            repo.save(mapper.convert(person));
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps", e);
        }
    }


    @Override
    public boolean aendern(final Person person) throws PersonenServiceException {
        try {

            checkPerson(person);
            if (!repo.existsById(person.getId())) return false;
            repo.save(mapper.convert(person));
            return true;
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps", e);
        }

    }

    @Override
    public boolean loeschen(final Person person) throws PersonenServiceException {
        return loeschen(person.getId());
    }

    @Override
    public boolean loeschen(final UUID id) throws PersonenServiceException {
        try {


            repo.deleteById(id);
            return true;
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps", e);
        }

    }

    @Override
    public Optional<Person> findeNachId(final UUID id) throws PersonenServiceException {
        try {
            return repo.findById(id).map(mapper::convert);
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps", e);
        }

    }

    @Override
    public Iterable<Person> findeAlle() throws PersonenServiceException {
        try {
            return mapper.convert(repo.findAll());
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps", e);
        }
    }

    private void checkPerson(final Person person) throws PersonenServiceException {
        validatePerson(person);
        businessCheck(person);
    }

    private void businessCheck(final Person person) throws PersonenServiceException {
        if (person.getVorname().equals("Attila")) throw new PersonenServiceException("Blacklisted Person");
    }

    private static void validatePerson(final Person person) throws PersonenServiceException {
        if (person == null) throw new PersonenServiceException("person mustr not be null");
        if (person.getVorname() == null || person.getVorname().length() < 2)
            throw new PersonenServiceException("firstname too short");
    }
}
