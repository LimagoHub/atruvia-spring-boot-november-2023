package de.atruvia.webapp.presentation.controller.schweine;

import de.atruvia.webapp.domain.service.PersonenService;
import de.atruvia.webapp.domain.service.PersonenServiceException;
import de.atruvia.webapp.domain.service.SchweineService;
import de.atruvia.webapp.domain.service.SchweineServiceException;
import de.atruvia.webapp.presentation.dto.PersonDto;
import de.atruvia.webapp.presentation.dto.SchweinDto;
import de.atruvia.webapp.presentation.mapper.PersonenDtoMapper;
import de.atruvia.webapp.presentation.mapper.SchweinDtoMapper;
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

import java.util.UUID;

@RestController
@RequestMapping("/v1/schweine")
@RequiredArgsConstructor
public class SchweineQueryController {

    private final SchweineService service;
    private final SchweinDtoMapper mapper;

//    @GetMapping(path="/demo/version", produces = MediaType.TEXT_PLAIN_VALUE)
//    public String version() {
//        return "1.0.0";
//    }


    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SchweinDto> findById(@Parameter(
            name = "id",
            description = "primaerschluessel der Person als UUID",
            example = "3fa85f64-5717-4562-b3fc-2c963f66afa6",
            required = true) @PathVariable UUID id) throws SchweineServiceException {


        return ResponseEntity.of(service.findeNachId(id).map(mapper::convert));
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<SchweinDto>> findAll() throws SchweineServiceException {

        return ResponseEntity.ok(mapper.convert(service.findeAlle()));
    }


}
