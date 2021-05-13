package com.digitalinnovationone.personapi.dto.request;

import com.digitalinnovationone.personapi.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {
    private Long id;

    @Enumerated(EnumType.STRING) // Indicando que o enumtype é do tipo string
    private PhoneType type;

    @NotEmpty
    @Size(min = 13, max = 14) // min de caracters do telefone
    private String number;
}
