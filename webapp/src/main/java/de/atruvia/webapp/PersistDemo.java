package de.atruvia.webapp;

import de.atruvia.webapp.persistence.PersonenRepository;
import de.atruvia.webapp.persistence.entity.PersonEntity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

//@Component
@RequiredArgsConstructor
public class PersistDemo {
    private final PersonenRepository repo;

    @PostConstruct
    public void foo() {
        var entity = PersonEntity.builder().id(UUID.randomUUID()).vorname("Erika").nachname("Mustermann").build();
        repo.persist(entity);
    }
}
