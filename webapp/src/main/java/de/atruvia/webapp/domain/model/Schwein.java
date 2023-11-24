package de.atruvia.webapp.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter(AccessLevel.PRIVATE)
public class Schwein {


    private UUID id;


    private String name;


    private int gewicht;

    public void taufen(String name) {
        if(name == null || "elsa".equalsIgnoreCase(name)) throw new IllegalArgumentException("Ungueltiger name");
        setName(name);
    }

    public void fuettern(){
        setGewicht(getGewicht() +  1);
    }

}
