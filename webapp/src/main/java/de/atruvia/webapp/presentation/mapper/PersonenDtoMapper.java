package de.atruvia.webapp.presentation.mapper;

import de.atruvia.webapp.domain.model.Person;
import de.atruvia.webapp.presentation.dto.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonenDtoMapper {

    Person convert(PersonDto personDto);

    PersonDto convert(Person person);

    Iterable<PersonDto> convert(Iterable<Person> personen);
}
