package com.agendador.bff_agendador.controller.dto.usuario;

import com.agendador.bff_agendador.controller.dto.endereco.EnderecoDTO;
import com.agendador.bff_agendador.controller.dto.telefone.TelefoneDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

    private String nome;
    private String email;
    private String senha;
    private Integer idade;
    private List<EnderecoDTO> enderecos;
    private List<TelefoneDTO> telefones;

}
