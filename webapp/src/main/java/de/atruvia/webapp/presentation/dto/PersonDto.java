package de.atruvia.webapp.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@XmlRootElement
public class PersonDto {


    private UUID id;

    @NotBlank
    @Size(min=2, max=30)
    private String vorname;

    @NotBlank
    @Size(min=2, max=30)
    private String nachname;


}
