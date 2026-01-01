package com.agendador.bff_agendador.controller.dto.usuario;

import com.agendador.bff_agendador.controller.dto.endereco.RequestEnderecoDTO;
import com.agendador.bff_agendador.controller.dto.telefone.RequestTelefoneDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestUsuarioDTO {

    private String nome;
    private String email;
    private String senha;
    private Integer idade;
    private List<RequestEnderecoDTO> enderecos;
    private List<RequestTelefoneDTO> telefones;

}
