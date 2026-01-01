package com.agendador.bff_agendador.controller.dto.login;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestLoginDTO {
    private String email;
    private String senha;
}
