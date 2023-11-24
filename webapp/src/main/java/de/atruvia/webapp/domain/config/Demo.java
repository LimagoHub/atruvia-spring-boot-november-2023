package de.atruvia.webapp.domain.config;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component

public class Demo {
    private final Set<String> mailconfig;
    private final MailConfig config;

    public Demo(final Set<String> mailconfig, final MailConfig config) {
        this.mailconfig = mailconfig;
        this.config = config;
        System.out.println(config);
    }
}
