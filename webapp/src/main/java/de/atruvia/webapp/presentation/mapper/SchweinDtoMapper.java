package de.atruvia.webapp.presentation.mapper;

import de.atruvia.webapp.domain.model.Schwein;
import de.atruvia.webapp.persistence.entity.SchweinEntity;
import de.atruvia.webapp.presentation.dto.SchweinDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SchweinDtoMapper {

    SchweinDto convert(Schwein schwein);
    Schwein convert(SchweinDto schwein);

    Iterable<SchweinDto> convert(Iterable<Schwein> schweine);
}
