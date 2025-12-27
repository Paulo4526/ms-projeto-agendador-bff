package com.agendador.bff_agendador.controller.dto.telefone;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneDTO {

    private Integer numero;
    private Integer ddd;

}
