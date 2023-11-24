package de.atruvia.webapp.presentation.controller.personen.version1;

import de.atruvia.webapp.domain.service.PersonenService;
import de.atruvia.webapp.domain.service.PersonenServiceException;
import de.atruvia.webapp.presentation.dto.PersonDto;
import de.atruvia.webapp.presentation.mapper.PersonenDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;


@RestController  // @Component
@RequestMapping("/v1/personen")
@RequiredArgsConstructor
public class PersonenCommandController {

    private final PersonenService service;
    private final PersonenDtoMapper mapper;


    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPerson(@Valid @RequestBody PersonDto dto, UriComponentsBuilder builder) throws PersonenServiceException {

        service.erfassen(mapper.convert(dto));
        UriComponents uriComponents = builder.path("/v1/personen/{id}").buildAndExpand(dto.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping(path="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePerson(@PathVariable UUID id,@Valid @RequestBody PersonDto dto) throws PersonenServiceException{
        if(! id.equals(dto.getId())) return ResponseEntity.badRequest().build();
       if(service.aendern(mapper.convert(dto))){
           return ResponseEntity.notFound().build();
       }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> removePerson(@PathVariable  UUID id) throws PersonenServiceException{
        if(service.loeschen(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}
