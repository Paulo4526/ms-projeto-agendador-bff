package com.agendador.bff_agendador.controller;

import com.agendador.bff_agendador.business.UsuarioService;
import com.agendador.bff_agendador.controller.dto.endereco.RequestEnderecoDTO;
import com.agendador.bff_agendador.controller.dto.endereco.ResponseEnderecoDTO;
import com.agendador.bff_agendador.controller.dto.login.RequestLoginDTO;
import com.agendador.bff_agendador.controller.dto.login.ResponseTokenDTO;
import com.agendador.bff_agendador.controller.dto.telefone.ResponseTelefoneDTO;
import com.agendador.bff_agendador.controller.dto.telefone.RequestTelefoneDTO;
import com.agendador.bff_agendador.controller.dto.usuario.ResponseUsuarioDTO;
import com.agendador.bff_agendador.controller.dto.usuario.RequestUsuarioDTO;
import com.agendador.bff_agendador.infrastructure.configs.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuario", description = "Cadastro e login de usuários")
//Anotação para ativarmos o Swagger com o Authorization na nossa aplição ativando o Security
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    //Dentro da anotação operation do Swagger, o parametro "security={}" remove a obrigatoriedade desse end-point ser autenticado
    @Operation(summary = "Salva usuários", description = "Cria um novo usuário", security = {})
    @ApiResponse(responseCode = "201", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "409", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ResponseUsuarioDTO> salvaUsuario(@RequestBody RequestUsuarioDTO requestUsuarioDTO){
        ResponseUsuarioDTO salvo = usuarioService.salvaUsuario(requestUsuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PostMapping("/login")
    //Dentro da anotação operation do Swagger, o parametro "security={}" remove a obrigatoriedade desse end-point ser autenticado
    @Operation(summary = "Faz login", description = "Faz login com usuário e senha", security = {})
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "409", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "401", description = "Credenciais do usuário inválidas!")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ResponseTokenDTO> login(@RequestBody RequestLoginDTO requestLoginDTO){
        return ResponseEntity.ok(usuarioService.login(requestLoginDTO));
    }

    @GetMapping("/{email}")
    @Operation(summary = "Procura usuário pelo email", description = "Realiza consulta e retorna o usuário pelo email")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Usuário informado é inválido!")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "401", description = "Credenciais do usuário inválidas!")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ResponseUsuarioDTO> findUserByEmail(
            @PathVariable String email,
            //Utilizar essa forma de anotação somente quando estivermos utilizando o swagger
            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.findUserByEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta o usuário pelo email", description = "Realiza a deleção do usuário pelo email")
    @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Usuário informado é inválido!")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "401", description = "Credenciais do usuário inválidas!")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deleteUserByEmail0
            (@PathVariable String email,
             //Utilizar essa forma de anotação somente quando estivermos utilizando o swagger
             @RequestHeader(name = "Authorization", required = false) String token){
        usuarioService.deleteUserByEmail(email, token);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Operation(summary = "Atualiza dados de usuários", description = "Realiza a atualização de dados do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Usuário informado é inválido!")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "401", description = "Credenciais do usuário inválidas!")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ResponseUsuarioDTO> atualizaUsuario(
            //Utilizar essa forma de anotação somente quando estivermos utilizando o swagger
            @RequestHeader(name = "Authorization", required = false) String token,
            @RequestBody RequestUsuarioDTO requestUsuarioDTO){
        return ResponseEntity.ok(usuarioService.atualizaUsuario(token, requestUsuarioDTO));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza dados de endereço", description = "Realiza a atualização de dados do endereço")
    @ApiResponse(responseCode = "200", description = "Endereço Atualizado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Id de endereço informado é inválido!")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "401", description = "Credenciais do usuário inválidas!")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ResponseEnderecoDTO> atualizaEndereco(
            @RequestBody RequestEnderecoDTO requestEnderecoDTO,
            @RequestParam("enderecoId") UUID id,
            //Utilizar essa forma de anotação somente quando estivermos utilizando o swagger
            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(requestEnderecoDTO, id, token));
    }

    @GetMapping("/endereco")
    @Operation(summary = "Busca endereço pelo ID", description = "Realiza a consulta de endereço pelo ID")
    @ApiResponse(responseCode = "200", description = "Endereço consultado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Id de endereço informado é inválido!")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "401", description = "Credenciais do usuário inválidas!")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ResponseEnderecoDTO> buscaEnderecoById(
            @RequestParam("enderecoId") UUID id,
            //Utilizar essa forma de anotação somente quando estivermos utilizando o swagger
            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.buscaEnderecoById(id, token));
    }

    @DeleteMapping("/endereco")
    @Operation(summary = "Deleta o endereço pelo ID", description = "Realiza a deleção de endereço paulo ID")
    @ApiResponse(responseCode = "204", description = "Endereço deletado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Id de endereço informado é inválido!")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "401", description = "Credenciais do usuário inválidas!")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deleteEnderecoById(
            @RequestParam("enderecoId") UUID id,
            //Utilizar essa forma de anotação somente quando estivermos utilizando o swagger
            @RequestHeader(name = "Authorization", required = false) String token){
        usuarioService.deleteEnderecoById(id, token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/endereco")
    @Operation(summary = "Cadastra dados de um novo endereço", description = "Realiza cadastro de um novo endereço")
    @ApiResponse(responseCode = "201", description = "Endereço cadastrado com sucesso!")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "401", description = "Credenciais do usuário inválidas!")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ResponseEnderecoDTO> cadastraNovoEndereco(
            //Utilizar essa forma de anotação somente quando estivermos utilizando o swagger
            @RequestHeader(name = "Authorization", required = false) String token,
            @RequestBody RequestEnderecoDTO requestEnderecoDTO){
        ResponseEnderecoDTO salvo = usuarioService.cadastraNovoEndereco(token, requestEnderecoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza dados de telefone", description = "Realiza a atualização de dados do telefone")
    @ApiResponse(responseCode = "200", description = "Telefone Atualizado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Id de telefone informado é inválido!")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "401", description = "Credenciais do usuário inválidas!")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ResponseTelefoneDTO> atualizaTelefone(
            @RequestBody RequestTelefoneDTO requestTelefoneDTO,
            @RequestParam("telefoneId") UUID id,
            //Utilizar essa forma de anotação somente quando estivermos utilizando o swagger
            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(requestTelefoneDTO, id, token));
    }

    @GetMapping("/telefone")
    @Operation(summary = "Realiza consulta de telefone pelo ID", description = "Realiza consulta de telefone pelo ID")
    @ApiResponse(responseCode = "200", description = "Telefone encontrado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Id de telefone informado é inválido!")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "401", description = "Credenciais do usuário inválidas!")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ResponseTelefoneDTO> buscaTelefoneById(
            @RequestParam("telefoneId") UUID id,
            //Utilizar essa forma de anotação somente quando estivermos utilizando o swagger
            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.buscaTelefoneById(id, token));
    }

    @DeleteMapping("/telefone")
    @Operation(summary = "Deleta dados de telefone", description = "Realiza a deleção de dados do telefone")
    @ApiResponse(responseCode = "204", description = "Telefone deletado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Id de telefone informado é inválido!")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "401", description = "Credenciais do usuário inválidas!")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deleteTelefoneById(
            @RequestParam("telefoneId") UUID id,
            //Utilizar essa forma de anotação somente quando estivermos utilizando o swagger
            @RequestHeader(name = "Authorization", required = false) String token){
        usuarioService.deleteTelefoneById(id, token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/telefone")
    @Operation(summary = "Cadastra novos dados de telefone", description = "Realiza o cadastro de novos dados do telefone")
    @ApiResponse(responseCode = "201", description = "Telefone cadastrado com sucesso!")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "401", description = "Credenciais do usuário inválidas!")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ResponseTelefoneDTO> cadastraNovoTelefone(
            //Utilizar essa forma de anotação somente quando estivermos utilizando o swagger
            @RequestHeader(name = "Authorization", required = false) String token,
            @RequestBody RequestTelefoneDTO requestTelefoneDTO){
        ResponseTelefoneDTO salvo = usuarioService.cadastraNovoTelefone(token, requestTelefoneDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }
}
