package de.atruvia.webapp.domain.config;

import de.atruvia.webapp.domain.mapper.PersonenMapper;
import de.atruvia.webapp.domain.service.internal.PersonenServiceImpl;
import de.atruvia.webapp.domain.service.PersonenService;
import de.atruvia.webapp.persistence.PersonenRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PersonenConfig {

    @Bean
    @Qualifier("blacklist")
    public List<String> createBlacklist() {
        return List.of("Attila", "Peter", "Paul", "Mary");
    }

    @Bean
    @Qualifier("fruits")
    public List<String> createFruits() {
        return List.of("Banana", "Strawberry", "cherry", "Rasperry");
    }

    @Bean
    public PersonenService createPersonenService(final PersonenRepository repo, final PersonenMapper mapper, @Qualifier("blacklist") final List<String> blacklist) {
        return new PersonenServiceImpl(repo,mapper,blacklist);
    }
}
