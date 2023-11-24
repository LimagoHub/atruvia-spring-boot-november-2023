package de.atruvia.webapp.presentation.controller.personen.version1;

import de.atruvia.webapp.domain.model.Person;
import de.atruvia.webapp.domain.service.PersonenService;
import de.atruvia.webapp.domain.service.PersonenServiceException;
import de.atruvia.webapp.presentation.dto.PersonDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
//@Sql({"/create.sql", "/insert.sql"})
class PersonenQueryControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private PersonenService personenServiceMock;
    @Test
    void findById() throws PersonenServiceException {
        final Optional<Person> optionalPerson = Optional.of(Person.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Doe").build());

        when(personenServiceMock.findeNachId(any())).thenReturn(optionalPerson);
        var personDto =  restTemplate.getForObject("/v1/personen/86dac2d5-7edc-483a-abc6-239e5b93eb13", PersonDto.class);

        assertEquals("John", personDto.getVorname());
    }

    @Test
    void test_2() throws PersonenServiceException {
        final Optional<Person> optionalPerson = Optional.of(Person.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Doe").build());

        when(personenServiceMock.findeNachId(any())).thenReturn(optionalPerson);
        var personDto =  restTemplate.getForObject("/v1/personen/86dac2d5-7edc-483a-abc6-239e5b93eb13", String.class);

        System.out.println(personDto);
    }

    @Test
    void test_3() throws PersonenServiceException {
        final Optional<Person> optionalPerson = Optional.of(Person.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Doe").build());

        when(personenServiceMock.findeNachId(any())).thenReturn(optionalPerson);
        var responseObject =  restTemplate.getForEntity("/v1/personen/86dac2d5-7edc-483a-abc6-239e5b93eb13", PersonDto.class);

        PersonDto personDto = responseObject.getBody();

        assertEquals("John", personDto.getVorname());
        assertEquals(HttpStatus.OK, responseObject.getStatusCode());
    }

    @Test
    void test_4() throws PersonenServiceException {

        when(personenServiceMock.findeNachId(any())).thenReturn(Optional.empty());
        var responseObject =  restTemplate.getForEntity("/v1/personen/86dac2d5-7edc-483a-abc6-239e5b93eb13", PersonDto.class);


        assertEquals(HttpStatus.NOT_FOUND, responseObject.getStatusCode());
    }

    @Test
    void test_4_a() throws PersonenServiceException {

        when(personenServiceMock.findeNachId(any())).thenReturn(Optional.empty());
        var responseObject =  restTemplate.exchange("/v1/personen/86dac2d5-7edc-483a-abc6-239e5b93eb13", HttpMethod.GET, null,PersonDto.class);


        assertEquals(HttpStatus.NOT_FOUND, responseObject.getStatusCode());
    }



    @Test
    void findAll() throws PersonenServiceException {

        var toSend = PersonDto.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Doe").build()

        HttpEntity<PersonDto> httpEntity = new HttpEntity<>(toSend);

        var personen = List.of(
                Person.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Doe").build(),
                Person.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Rambo").build(),
                Person.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Wayne").build()
        );

        when(personenServiceMock.findeAlle()).thenReturn(personen);
        var responseObject =  restTemplate.exchange("/v1/personen",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<PersonDto>>() { });


        assertEquals(3, responseObject.getBody().size());
    }
}