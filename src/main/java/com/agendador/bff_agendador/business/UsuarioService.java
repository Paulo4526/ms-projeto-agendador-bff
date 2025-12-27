package com.agendador.bff_agendador.business;

import com.agendador.bff_agendador.controller.dto.endereco.EnderecoDTO;
import com.agendador.bff_agendador.controller.dto.endereco.ShowEnderecoDTO;
import com.agendador.bff_agendador.controller.dto.login.LoginDTO;
import com.agendador.bff_agendador.controller.dto.login.TokenDTO;
import com.agendador.bff_agendador.controller.dto.telefone.ShowTelefoneDTO;
import com.agendador.bff_agendador.controller.dto.telefone.TelefoneDTO;
import com.agendador.bff_agendador.controller.dto.usuario.ShowUsuarioDTO;
import com.agendador.bff_agendador.controller.dto.usuario.UsuarioDTO;
import com.agendador.bff_agendador.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    private final UsuarioClient usuarioClient;

    public ShowUsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        return usuarioClient.salvaUsuario(usuarioDTO);
    }

    public TokenDTO login (LoginDTO loginDTO){
        return usuarioClient.login(loginDTO);
    }

    public ShowUsuarioDTO findUserByEmail(String email, String token){
        return usuarioClient.findUserByEmail(email, token);
    }

    public Void deleteUserByEmail(String email, String token){
        return usuarioClient.deleteUserByEmail(email, token);
    }

    public ShowUsuarioDTO atualizaUsuario(String token, UsuarioDTO usuarioDTO){
        return usuarioClient.atualizaUsuario(token, usuarioDTO);
    }

    public ShowEnderecoDTO atualizaEndereco(EnderecoDTO enderecoDTO, UUID uuid, String token){
        return  usuarioClient.atualizaEndereco(enderecoDTO, uuid, token);
    }

    public ShowEnderecoDTO buscaEnderecoById(UUID id, String token){
        return  usuarioClient.buscaEnderecoById(id, token);
    }

    public Void deleteEnderecoById(UUID id, String token){
        return usuarioClient.deleteEnderecoById(id, token);
    }

    public ShowEnderecoDTO cadastraNovoEndereco(String token, EnderecoDTO enderecoDTO){
        return usuarioClient.cadastraNovoEndereco(token, enderecoDTO);
    }

    public ShowTelefoneDTO atualizaTelefone(TelefoneDTO telefoneDTO, UUID id, String token){
        return usuarioClient.atualizaTelefone(telefoneDTO, id, token);
    }

    public ShowTelefoneDTO buscaTelefoneById(UUID id, String token){
        return usuarioClient.buscaTelefoneById(id, token);
    }

    public Void deleteTelefoneById(UUID id, String token){
        return usuarioClient.deleteTelefoneById(id, token);
    }

    public ShowTelefoneDTO cadastraNovoTelefone(String token, TelefoneDTO telefoneDTO){
        return usuarioClient.cadastraNovoTelefone(token, telefoneDTO);
    }
}
