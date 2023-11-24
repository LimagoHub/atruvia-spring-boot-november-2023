package de.atruvia.webapp.domain.service.internal;

import de.atruvia.webapp.domain.mapper.PersonenMapper;
import de.atruvia.webapp.domain.model.Person;
import de.atruvia.webapp.domain.service.PersonenService;
import de.atruvia.webapp.domain.service.PersonenServiceException;
import de.atruvia.webapp.persistence.PersonenRepository;
import de.atruvia.webapp.persistence.entity.PersonEntity;
import de.atruvia.webapp.presentation.mapper.PersonenDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonenServiceImplTest {


    private PersonenServiceImpl objectUnderTest;

    private PersonenRepository repositoryMock;

    private PersonenMapper mapperMock;

    @BeforeEach
    public void setUp() {
        repositoryMock = Mockito.mock(PersonenRepository.class);
        mapperMock = Mockito.mock(PersonenMapper.class);

        objectUnderTest = new PersonenServiceImpl(repositoryMock, mapperMock, Collections.emptyList());
    }

    @Test
    void erfassen__unexpected_exception_in_underlying_service__throwsPersonenServiceException() {

        // Arrange
        Person validPerson = Person.builder().id(UUID.randomUUID()).vorname("John").nachname("Doe").build();

        Mockito.when(repositoryMock.save(Mockito.any())).thenThrow(new ArrayIndexOutOfBoundsException("Häh"));

        // Act
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.erfassen(validPerson));

        // Assertion
        assertEquals("Echt jetzt?", ex.getMessage());
        assertEquals(ArrayIndexOutOfBoundsException.class, ex.getCause().getClass());
    }
}