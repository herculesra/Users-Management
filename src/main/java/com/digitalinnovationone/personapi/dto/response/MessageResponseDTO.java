package com.digitalinnovationone.personapi.dto.response;

import lombok.Builder;
import lombok.Data;

@Data //Cria os geters, seters, equals e hashcode.
@Builder
public class MessageResponseDTO {
    private String message;
}
