package de.atruvia.webapp.presentation.controller.personen.version1;

import de.atruvia.webapp.domain.PersonenService;
import de.atruvia.webapp.domain.PersonenServiceException;
import de.atruvia.webapp.presentation.dto.PersonDto;
import de.atruvia.webapp.presentation.mapper.PersonenDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/personen")
@RequiredArgsConstructor
public class PersonenQueryController {

    private final PersonenService service;
    private final PersonenDtoMapper mapper;

//    @GetMapping(path="/demo/version", produces = MediaType.TEXT_PLAIN_VALUE)
//    public String version() {
//        return "1.0.0";
//    }

    @Operation(summary = "Liefert eine Person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person gefunden",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDto.class)) }),
            @ApiResponse(responseCode = "400", description = "ungueltige ID",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Person nicht gefunden",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error",
                    content = @Content)})

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDto> findById(@Parameter(
            name =  "id",
            description  = "primaerschluessel der Person als UUID",
            example = "3fa85f64-5717-4562-b3fc-2c963f66afa6",
            required = true) @PathVariable  UUID id) throws PersonenServiceException {


        return ResponseEntity.of(service.findeNachId(id).map(mapper::convert));
    }

    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<PersonDto>> findAll(
            @RequestParam(required = false, defaultValue = "John") String vorname,
            @RequestParam(required = false, defaultValue = "Doe")String nachname
    ) throws PersonenServiceException{

        System.out.println(String.format("Vorname: %s, nachname %s", vorname, nachname));


        return ResponseEntity.ok(mapper.convert(service.findeAlle()));
    }

    @PostMapping(path = "/functions/toupper", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> toUpper(@RequestBody PersonDto dto) {

        dto.setVorname(dto.getVorname().toUpperCase());
        dto.setNachname(dto.getNachname().toUpperCase());

        return ResponseEntity.ok(dto);
    }


}
