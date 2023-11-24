package de.atruvia.webapp.presentation.controller.schweine;



import de.atruvia.webapp.domain.service.SchweineService;
import de.atruvia.webapp.domain.service.SchweineServiceException;
import de.atruvia.webapp.presentation.dto.SchweinDto;
import de.atruvia.webapp.presentation.mapper.SchweinDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;


@RestController  // @Component
@RequestMapping("/v1/schweine")
@RequiredArgsConstructor
public class SchweinCommandController {

    private final SchweineService service;
    private final SchweinDtoMapper mapper;


    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createSchwein(@Valid @RequestBody SchweinDto dto, UriComponentsBuilder builder) throws SchweineServiceException {

        service.erfassen(mapper.convert(dto));
        UriComponents uriComponents = builder.path("/v1/schweine/{id}").buildAndExpand(dto.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping(path="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateSchwein(@PathVariable UUID id, @Valid @RequestBody SchweinDto dto) throws SchweineServiceException{
        if(! id.equals(dto.getId())) return ResponseEntity.badRequest().build();
       if(service.aendern(mapper.convert(dto))){
           return ResponseEntity.notFound().build();
       }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> removeSchwein(@PathVariable  UUID id) throws SchweineServiceException{
        if(service.loeschen(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping(path="/{id}/fuettern")
    public ResponseEntity<Void>  fuettern(@PathVariable UUID id, @RequestParam(required = false, defaultValue = "1") int anzahlKartoffel)  throws SchweineServiceException{
        if(service.fuettern(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }

}
