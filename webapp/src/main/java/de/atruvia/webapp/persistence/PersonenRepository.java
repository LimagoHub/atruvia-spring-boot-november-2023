package de.atruvia.webapp.persistence;

import de.atruvia.webapp.persistence.entity.PersonEntity;
import de.atruvia.webapp.persistence.entity.TinyPerson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PersonenRepository extends CrudRepository<PersonEntity, UUID> {

    @Query("select p from PersonEntity p where p.nachname=:nachname")
    Iterable<PersonEntity> xyz(String nachname);

    @Query("update PersonEntity p set p.vorname=:vorname where p.id=:id")
    void update(UUID id, String vorname) ;

    Iterable<PersonEntity> findByVorname(String vorname);

    @Query("select new de.atruvia.webapp.persistence.entity.TinyPerson(p.id, p.nachname) from PersonEntity p")
    Iterable<TinyPerson> findAllTinyPersons();
}
