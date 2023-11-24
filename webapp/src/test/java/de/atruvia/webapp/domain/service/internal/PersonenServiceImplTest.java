package de.atruvia.webapp.domain.service.internal;

import de.atruvia.webapp.domain.mapper.PersonenMapper;
import de.atruvia.webapp.domain.model.Person;
import de.atruvia.webapp.domain.service.PersonenService;
import de.atruvia.webapp.domain.service.PersonenServiceException;
import de.atruvia.webapp.persistence.PersonenRepository;
import de.atruvia.webapp.persistence.entity.PersonEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonenServiceImplTest {

    @InjectMocks
    private PersonenServiceImpl objectUnderTest;
    @Mock
    private PersonenRepository repositoryMock;
    @Mock
    private PersonenMapper mapperMock;

    @Test
    void erfassen__unexpected_exception_in_underlying_service__throwsPersonenServiceException() {
        Person validPerson = Person.builder().id(UUID.randomUUID()).vorname("John").nachname("Doe").build();

        Mockito.when(repositoryMock.save(Mockito.any())).thenThrow(new ArrayIndexOutOfBoundsException("Häh"));

        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.erfassen(validPerson));

        assertEquals("Echt jetzt?", ex.getMessage());
        assertEquals(ArrayIndexOutOfBoundsException.class, ex.getCause().getClass());
    }
}