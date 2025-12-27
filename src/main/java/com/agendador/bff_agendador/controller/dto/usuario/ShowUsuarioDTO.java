package com.agendador.bff_agendador.controller.dto.usuario;


import com.agendador.bff_agendador.controller.dto.endereco.ShowEnderecoDTO;
import com.agendador.bff_agendador.controller.dto.telefone.ShowTelefoneDTO;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowUsuarioDTO {

    private UUID id;
    private String nome;
    private String email;
    private String senha;
    private Integer idade;
    private List<ShowEnderecoDTO> enderecos;
    private List<ShowTelefoneDTO> telefones;

}