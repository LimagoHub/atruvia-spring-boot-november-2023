package de.atruvia.simplespring;

import de.atruvia.simplespring.client.CalcClient;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(10)
@RequiredArgsConstructor
public class MyRunner implements CommandLineRunner {

    private final CalcClient client;
    @Override
    public void run(final String... args) throws Exception {
        client.go();
    }
}
