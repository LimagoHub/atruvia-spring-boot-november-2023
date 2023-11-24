package de.atruvia.webapp.domain.service.internal;


import de.atruvia.webapp.domain.mapper.SchweinMapper;
import de.atruvia.webapp.domain.model.Schwein;
import de.atruvia.webapp.domain.service.PersonenServiceException;
import de.atruvia.webapp.domain.service.SchweineService;
import de.atruvia.webapp.domain.service.SchweineServiceException;
import de.atruvia.webapp.persistence.SchweineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = PersonenServiceException.class, isolation = Isolation.READ_COMMITTED)
@RequiredArgsConstructor
public class SchweineServiceImpl implements SchweineService {


    private final SchweineRepository repo;
    private final SchweinMapper mapper;


    @Override
    public void erfassen(final Schwein schwein) throws SchweineServiceException {
        try {
            checkSchwein(schwein);
            if (repo.existsById(schwein.getId())) throw new SchweineServiceException("Person already exists");
            repo.save(mapper.convert(schwein
            ));
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Upps", e);
        }
    }


    @Override
    public boolean aendern(final Schwein schwein
    ) throws SchweineServiceException {
        try {

            checkSchwein(schwein);
            if (!repo.existsById(schwein
                    .getId())) return false;
            repo.save(mapper.convert(schwein
            ));
            return true;
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Upps", e);
        }

    }

    @Override
    public boolean loeschen(final Schwein schwein
    ) throws SchweineServiceException {
        return loeschen(schwein
                .getId());
    }

    @Override
    public boolean loeschen(final UUID id) throws SchweineServiceException {
        try {


            repo.deleteById(id);
            return true;
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Upps", e);
        }

    }

    @Override
    public Optional<Schwein> findeNachId(final UUID id) throws SchweineServiceException {
        try {
            return repo.findById(id).map(mapper::convert);
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Upps", e);
        }

    }

    @Override
    public Iterable<Schwein> findeAlle() throws SchweineServiceException {
        try {
            return mapper.convert(repo.findAll());
        } catch (RuntimeException e) {
            throw new SchweineServiceException("Upps", e);
        }
    }

    @Override
    public boolean fuettern(final UUID id) throws SchweineServiceException {


        //findeNachId(id).map(schwein -> {schwein.fuettern();return schwein;}).

        Optional<Schwein> schweinOptional = findeNachId(id);
        if(schweinOptional.isEmpty()) return false;
        Schwein piggy = schweinOptional.get();
        piggy.fuettern();
        aendern(piggy);
        return true;
    }

    private void checkSchwein(final Schwein schwein
    ) throws SchweineServiceException {
        validatePerson(schwein);
        businessCheck(schwein);
    }

    private void businessCheck(final Schwein schwein
    ) throws SchweineServiceException {
        if (schwein.getName().equals("Attila")) throw new SchweineServiceException("Blacklisted Person");
    }

    private static void validatePerson(final Schwein schwein) throws SchweineServiceException {
        if (schwein == null) throw new SchweineServiceException("schwein mustr not be null");
        if (schwein.getName() == null || schwein.getName().length() < 2)
            throw new SchweineServiceException("firstname too short");
    }
}
