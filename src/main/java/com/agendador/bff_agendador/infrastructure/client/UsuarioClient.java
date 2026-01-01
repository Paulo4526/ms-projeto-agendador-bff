package com.agendador.bff_agendador.infrastructure.client;

import com.agendador.bff_agendador.controller.dto.endereco.RequestEnderecoDTO;
import com.agendador.bff_agendador.controller.dto.endereco.ResponseEnderecoDTO;
import com.agendador.bff_agendador.controller.dto.login.RequestLoginDTO;
import com.agendador.bff_agendador.controller.dto.login.ResponseTokenDTO;
import com.agendador.bff_agendador.controller.dto.telefone.ResponseTelefoneDTO;
import com.agendador.bff_agendador.controller.dto.telefone.RequestTelefoneDTO;
import com.agendador.bff_agendador.controller.dto.usuario.ResponseUsuarioDTO;
import com.agendador.bff_agendador.controller.dto.usuario.RequestUsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

//Diferente das outras classes, utilizamos o path usuário para todas as requisições dessa classe, para demonstrar as formas possíveis de utilização
@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuario")
    ResponseUsuarioDTO buscaUsuarioPorEmail(@RequestParam("email") String email, @RequestHeader("Authorization") String token);

    @PostMapping("/usuario")
    ResponseUsuarioDTO salvaUsuario(@RequestBody RequestUsuarioDTO requestUsuarioDTO);

    @PostMapping("/usuario/login")
    ResponseTokenDTO login(@RequestBody RequestLoginDTO requestLoginDTO);

    @GetMapping("/usuario/{email}")
    ResponseUsuarioDTO findUserByEmail(@PathVariable String email, @RequestHeader("Authorization") String token);

    @DeleteMapping("/usuario/{email}")
    Void deleteUserByEmail(@PathVariable String email, @RequestHeader("Authorization") String token);

    @PutMapping("/usuario")
    ResponseUsuarioDTO atualizaUsuario(@RequestHeader("Authorization") String token, @RequestBody RequestUsuarioDTO requestUsuarioDTO);

    @PutMapping("/usuario/endereco")
    ResponseEnderecoDTO atualizaEndereco(@RequestBody RequestEnderecoDTO requestEnderecoDTO, @RequestParam("enderecoId") UUID id, @RequestHeader("Authorization") String token);

    @GetMapping("/usuario/endereco")
    ResponseEnderecoDTO buscaEnderecoById(@RequestParam("enderecoId") UUID id, @RequestHeader("Authorization") String token);

    @DeleteMapping("/usuario/endereco")
    Void deleteEnderecoById(@RequestParam("enderecoId") UUID id, @RequestHeader("Authorization") String token);

    @PostMapping("/usuario/endereco")
    ResponseEnderecoDTO cadastraNovoEndereco(@RequestHeader("Authorization") String token, @RequestBody RequestEnderecoDTO requestEnderecoDTO);

    @PutMapping("/usuario/telefone")
    ResponseTelefoneDTO atualizaTelefone(@RequestBody RequestTelefoneDTO requestTelefoneDTO, @RequestParam("telefoneId") UUID id, @RequestHeader("Authorization") String token);

    @GetMapping("/usuario/telefone")
    ResponseTelefoneDTO buscaTelefoneById(@RequestParam("telefoneId") UUID id, @RequestHeader("Authorization") String token);

    @DeleteMapping("/usuario/telefone")
    Void deleteTelefoneById(@RequestParam("telefoneId") UUID id, @RequestHeader("Authorization") String token);

    @PostMapping("/usuario/telefone")
    ResponseTelefoneDTO cadastraNovoTelefone(@RequestHeader("Authorization") String token, @RequestBody RequestTelefoneDTO requestTelefoneDTO);

}
