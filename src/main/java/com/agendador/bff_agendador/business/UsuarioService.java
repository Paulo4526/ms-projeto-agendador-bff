package com.agendador.bff_agendador.business;

import com.agendador.bff_agendador.controller.dto.endereco.RequestEnderecoDTO;
import com.agendador.bff_agendador.controller.dto.endereco.ResponseEnderecoDTO;
import com.agendador.bff_agendador.controller.dto.login.RequestLoginDTO;
import com.agendador.bff_agendador.controller.dto.login.ResponseTokenDTO;
import com.agendador.bff_agendador.controller.dto.telefone.ResponseTelefoneDTO;
import com.agendador.bff_agendador.controller.dto.telefone.RequestTelefoneDTO;
import com.agendador.bff_agendador.controller.dto.usuario.ResponseUsuarioDTO;
import com.agendador.bff_agendador.controller.dto.usuario.RequestUsuarioDTO;
import com.agendador.bff_agendador.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    private final UsuarioClient usuarioClient;

    public ResponseUsuarioDTO salvaUsuario(RequestUsuarioDTO requestUsuarioDTO){
        return usuarioClient.salvaUsuario(requestUsuarioDTO);
    }

    public ResponseTokenDTO login (RequestLoginDTO requestLoginDTO){
        return usuarioClient.login(requestLoginDTO);
    }

    public ResponseUsuarioDTO findUserByEmail(String email, String token){
        return usuarioClient.findUserByEmail(email, token);
    }

    public Void deleteUserByEmail(String email, String token){
        return usuarioClient.deleteUserByEmail(email, token);
    }

    public ResponseUsuarioDTO atualizaUsuario(String token, RequestUsuarioDTO requestUsuarioDTO){
        return usuarioClient.atualizaUsuario(token, requestUsuarioDTO);
    }

    public ResponseEnderecoDTO atualizaEndereco(RequestEnderecoDTO requestEnderecoDTO, UUID uuid, String token){
        return  usuarioClient.atualizaEndereco(requestEnderecoDTO, uuid, token);
    }

    public ResponseEnderecoDTO buscaEnderecoById(UUID id, String token){
        return  usuarioClient.buscaEnderecoById(id, token);
    }

    public Void deleteEnderecoById(UUID id, String token){
        return usuarioClient.deleteEnderecoById(id, token);
    }

    public ResponseEnderecoDTO cadastraNovoEndereco(String token, RequestEnderecoDTO requestEnderecoDTO){
        return usuarioClient.cadastraNovoEndereco(token, requestEnderecoDTO);
    }

    public ResponseTelefoneDTO atualizaTelefone(RequestTelefoneDTO requestTelefoneDTO, UUID id, String token){
        return usuarioClient.atualizaTelefone(requestTelefoneDTO, id, token);
    }

    public ResponseTelefoneDTO buscaTelefoneById(UUID id, String token){
        return usuarioClient.buscaTelefoneById(id, token);
    }

    public Void deleteTelefoneById(UUID id, String token){
        return usuarioClient.deleteTelefoneById(id, token);
    }

    public ResponseTelefoneDTO cadastraNovoTelefone(String token, RequestTelefoneDTO requestTelefoneDTO){
        return usuarioClient.cadastraNovoTelefone(token, requestTelefoneDTO);
    }
}
