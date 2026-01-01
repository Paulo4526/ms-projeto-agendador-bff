package com.agendador.bff_agendador.controller.dto.usuario;


import com.agendador.bff_agendador.controller.dto.endereco.ResponseEnderecoDTO;
import com.agendador.bff_agendador.controller.dto.telefone.ResponseTelefoneDTO;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseUsuarioDTO {

    private UUID id;
    private String nome;
    private String email;
    private String senha;
    private Integer idade;
    private List<ResponseEnderecoDTO> enderecos;
    private List<ResponseTelefoneDTO> telefones;

}