package com.agendador.bff_agendador.controller.dto.telefone;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseTelefoneDTO {
    private UUID id;
    private Integer numero;
    private Integer ddd;
}
