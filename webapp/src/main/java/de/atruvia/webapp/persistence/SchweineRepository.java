package de.atruvia.webapp.persistence;

import de.atruvia.webapp.persistence.entity.SchweinEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SchweineRepository extends CrudRepository<SchweinEntity, UUID> {

    Iterable<SchweinEntity> findByName(String name);
}
