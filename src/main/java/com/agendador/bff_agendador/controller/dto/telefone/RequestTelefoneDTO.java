package com.agendador.bff_agendador.controller.dto.telefone;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestTelefoneDTO {

    private Integer numero;
    private Integer ddd;

}
