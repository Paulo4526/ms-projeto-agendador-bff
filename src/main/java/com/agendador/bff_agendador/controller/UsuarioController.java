package com.agendador.bff_agendador.controller;

import com.agendador.bff_agendador.business.UsuarioService;
import com.agendador.bff_agendador.controller.dto.endereco.EnderecoDTO;
import com.agendador.bff_agendador.controller.dto.endereco.ShowEnderecoDTO;
import com.agendador.bff_agendador.controller.dto.login.LoginDTO;
import com.agendador.bff_agendador.controller.dto.login.TokenDTO;
import com.agendador.bff_agendador.controller.dto.telefone.ShowTelefoneDTO;
import com.agendador.bff_agendador.controller.dto.telefone.TelefoneDTO;
import com.agendador.bff_agendador.controller.dto.usuario.ShowUsuarioDTO;
import com.agendador.bff_agendador.controller.dto.usuario.UsuarioDTO;
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
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ShowUsuarioDTO> salvaUsuario(@RequestBody UsuarioDTO usuarioDTO){
        ShowUsuarioDTO salvo = usuarioService.salvaUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PostMapping("/login")
    //Dentro da anotação operation do Swagger, o parametro "security={}" remove a obrigatoriedade desse end-point ser autenticado
    @Operation(summary = "Faz login", description = "Faz login com usuário e senha", security = {})
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "400", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO){
        return ResponseEntity.ok(usuarioService.login(loginDTO));
    }

    @GetMapping("/{email}")
    @Operation(summary = "Procura usuário pelo email", description = "Realiza consulta e retorna o usuário pelo email")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Usuário informado é inválido!")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ShowUsuarioDTO> findUserByEmail(
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
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ShowUsuarioDTO> atualizaUsuario(
            //Utilizar essa forma de anotação somente quando estivermos utilizando o swagger
            @RequestHeader(name = "Authorization", required = false) String token,
            @RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioService.atualizaUsuario(token, usuarioDTO));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza dados de endereço", description = "Realiza a atualização de dados do endereço")
    @ApiResponse(responseCode = "200", description = "Endereço Atualizado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Id de endereço informado é inválido!")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ShowEnderecoDTO> atualizaEndereco(
            @RequestBody EnderecoDTO enderecoDTO,
            @RequestParam("enderecoId") UUID id,
            //Utilizar essa forma de anotação somente quando estivermos utilizando o swagger
            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(enderecoDTO, id, token));
    }

    @GetMapping("/endereco")
    @Operation(summary = "Busca endereço pelo ID", description = "Realiza a consulta de endereço pelo ID")
    @ApiResponse(responseCode = "200", description = "Endereço consultado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Id de endereço informado é inválido!")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ShowEnderecoDTO> buscaEnderecoById(
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
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ShowEnderecoDTO> cadastraNovoEndereco(
            //Utilizar essa forma de anotação somente quando estivermos utilizando o swagger
            @RequestHeader(name = "Authorization", required = false) String token,
            @RequestBody EnderecoDTO enderecoDTO){
        ShowEnderecoDTO salvo = usuarioService.cadastraNovoEndereco(token, enderecoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza dados de telefone", description = "Realiza a atualização de dados do telefone")
    @ApiResponse(responseCode = "200", description = "Telefone Atualizado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Id de telefone informado é inválido!")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ShowTelefoneDTO> atualizaTelefone(
            @RequestBody TelefoneDTO telefoneDTO,
            @RequestParam("telefoneId") UUID id,
            //Utilizar essa forma de anotação somente quando estivermos utilizando o swagger
            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(telefoneDTO, id, token));
    }

    @GetMapping("/telefone")
    @Operation(summary = "Realiza consulta de telefone pelo ID", description = "Realiza consulta de telefone pelo ID")
    @ApiResponse(responseCode = "200", description = "Telefone encontrado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Id de telefone informado é inválido!")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ShowTelefoneDTO> buscaTelefoneById(
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
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ShowTelefoneDTO> cadastraNovoTelefone(
            //Utilizar essa forma de anotação somente quando estivermos utilizando o swagger
            @RequestHeader(name = "Authorization", required = false) String token,
            @RequestBody TelefoneDTO telefoneDTO){
        ShowTelefoneDTO salvo = usuarioService.cadastraNovoTelefone(token, telefoneDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }
}
