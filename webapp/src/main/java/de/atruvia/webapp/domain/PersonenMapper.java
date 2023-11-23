package de.atruvia.webapp.domain;

import de.atruvia.webapp.persistence.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonenMapper {

    Person convert(PersonEntity personEntity);

    PersonEntity convert(Person person);

    Iterable<Person> convert(Iterable<PersonEntity> entities);
}
