package com.agendador.bff_agendador.controller.dto.telefone;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowTelefoneDTO {
    private UUID id;
    private Integer numero;
    private Integer ddd;
}
