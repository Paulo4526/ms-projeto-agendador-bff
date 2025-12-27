package com.agendador.bff_agendador.controller;

import com.agendador.bff_agendador.business.TarefaService;
import com.agendador.bff_agendador.controller.dto.tarefas.ShowTarefaDTO;
import com.agendador.bff_agendador.controller.dto.tarefas.TarefasDTO;
import com.agendador.bff_agendador.infrastructure.configs.security.SecurityConfig;
import com.agendador.bff_agendador.infrastructure.enums.StatusNotificacaoEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tarefas")
@Tag(name = "Tarefas", description = "Controller para tarefas")
//Anotação para ativarmos o Swagger com o Authorization na nossa aplição
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasControler {

    private final TarefaService tarefaService;

    @PostMapping
    @Operation(summary = "Salva novas tarefas", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso!")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ShowTarefaDTO> gravaTarefa(
            @RequestBody TarefasDTO tarefasDTO,
            //Utilizar essa forma de anotação somente quando estivermos utilizando o swagger
            @RequestHeader(name = "Authorization", required = false) String token){

        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.gravarTarefa(tarefasDTO, token));

    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefas por periodo", description = "Busca tarefas por periodo")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<ShowTarefaDTO>> listarTarefasAgendadasEntreDatas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataEventoInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataEventoFinal,
            //Utilizar essa forma de anotação somente quando estivermos utilizando o swagger
            @RequestHeader(name = "Authorization", required = false) String token){

        return ResponseEntity.ok(tarefaService.buscarTarefasAgendadas(dataEventoInicio, dataEventoFinal, token));

    }

    @GetMapping
    @Operation(summary = "Busca tarefas por email de usuario", description = "Buscando tarefas pelo email de usuario")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<ShowTarefaDTO>> buscarTarefasPeloEmail(
            //Utilizar essa forma de anotação somente quando estivermos utilizando o swagger
            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.buscarTarefasPeloEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefa pelo id", description = "Deleta tarefa pelo id")
    @ApiResponse(responseCode = "204", description = "Tarefa deletada com sucesso!")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deleteById(
            @RequestParam("id") String id,
            //Utilizar essa forma de anotação somente quando estivermos utilizando o swagger
            @RequestHeader(name = "Authorization", required = false) String token){
        tarefaService.deletaTarefaPorId(id, token);
        return ResponseEntity.noContent().build();

    }

    @PatchMapping("/status")
    @Operation(summary = "Altera status da tarefa", description = "Altera status da tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa alterada com sucesso!")
    @ApiResponse(responseCode = "400", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ShowTarefaDTO> alteraStatusNotificacao(
            @RequestParam("status") StatusNotificacaoEnum status,
            @RequestParam("id") String id,
            //Utilizar essa forma de anotação somente quando estivermos utilizando o swagger
            @RequestHeader(name = "Authorization", required = false) String token){

        return ResponseEntity.ok(tarefaService.alteraStatus(status, id, token));
    }

    @PutMapping("/updateTask")
    @Operation(summary = "Altera dados da tarefa", description = "Altera dados da tarefa")
    @ApiResponse(responseCode = "200", description = "Terefa atualizada com sucesso!")
    @ApiResponse(responseCode = "400", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "403", description = "Operação não permitida/indevida")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ShowTarefaDTO> alteraTarefas(
            @RequestBody TarefasDTO tarefasDTO,
            @RequestParam("id") String id,
            //Utilizar essa forma de anotação somente quando estivermos utilizando o swagger
            @RequestHeader(name = "Authorization", required = false) String token){

        return ResponseEntity.ok(tarefaService.updateTarefas(tarefasDTO, id, token));
    }
}
