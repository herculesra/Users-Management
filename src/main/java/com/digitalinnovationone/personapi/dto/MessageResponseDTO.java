package com.digitalinnovationone.personapi.dto;

import lombok.Builder;
import lombok.Data;

@Data //Cria os geters, seters, equals e hashcode.
@Builder
public class MessageResponseDTO {
    private String message;
}
