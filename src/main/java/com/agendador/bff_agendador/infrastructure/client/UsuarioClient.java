package com.agendador.bff_agendador.infrastructure.client;

import com.agendador.bff_agendador.controller.dto.endereco.EnderecoDTO;
import com.agendador.bff_agendador.controller.dto.endereco.ShowEnderecoDTO;
import com.agendador.bff_agendador.controller.dto.login.LoginDTO;
import com.agendador.bff_agendador.controller.dto.login.TokenDTO;
import com.agendador.bff_agendador.controller.dto.telefone.ShowTelefoneDTO;
import com.agendador.bff_agendador.controller.dto.telefone.TelefoneDTO;
import com.agendador.bff_agendador.controller.dto.usuario.ShowUsuarioDTO;
import com.agendador.bff_agendador.controller.dto.usuario.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuario")
    ShowUsuarioDTO buscaUsuarioPorEmail(@RequestParam("email") String email, @RequestHeader("Authorization") String token);

    @PostMapping
    ShowUsuarioDTO salvaUsuario(@RequestBody UsuarioDTO usuarioDTO);

    @PostMapping("/usuario/login")
    TokenDTO login(@RequestBody LoginDTO loginDTO);

    @GetMapping("/usuario/{email}")
    ShowUsuarioDTO findUserByEmail(@PathVariable String email, @RequestHeader("Authorization") String token);

    @DeleteMapping("/usuario/{email}")
    Void deleteUserByEmail(@PathVariable String email, @RequestHeader("Authorization") String token);

    @PutMapping
    ShowUsuarioDTO atualizaUsuario(@RequestHeader("Authorization") String token,@RequestBody UsuarioDTO usuarioDTO);

    @PutMapping("/usuario/endereco")
    ShowEnderecoDTO atualizaEndereco(@RequestBody EnderecoDTO enderecoDTO, @RequestParam("enderecoId") UUID id, @RequestHeader("Authorization") String token);

    @GetMapping("/usuario/endereco")
    ShowEnderecoDTO buscaEnderecoById(@RequestParam("enderecoId") UUID id, @RequestHeader("Authorization") String token);

    @DeleteMapping("/usuario/endereco")
    Void deleteEnderecoById(@RequestParam("enderecoId") UUID id, @RequestHeader("Authorization") String token);

    @PostMapping("/usuario/endereco")
    ShowEnderecoDTO cadastraNovoEndereco(@RequestHeader("Authorization") String token, @RequestBody EnderecoDTO enderecoDTO);

    @PutMapping("/usuario/telefone")
    ShowTelefoneDTO atualizaTelefone(@RequestBody TelefoneDTO telefoneDTO, @RequestParam("telefoneId") UUID id, @RequestHeader("Authorization") String token);

    @GetMapping("/usuario/telefone")
    ShowTelefoneDTO buscaTelefoneById(@RequestParam("telefoneId") UUID id, @RequestHeader("Authorization") String token);

    @DeleteMapping("/usuario/telefone")
    Void deleteTelefoneById(@RequestParam("telefoneId") UUID id, @RequestHeader("Authorization") String token);

    @PostMapping("/usuario/telefone")
    ShowTelefoneDTO cadastraNovoTelefone(@RequestHeader("Authorization") String token, @RequestBody TelefoneDTO telefoneDTO);

}
