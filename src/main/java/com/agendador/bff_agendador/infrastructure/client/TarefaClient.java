package com.agendador.bff_agendador.infrastructure.client;

import com.agendador.bff_agendador.controller.dto.tarefas.ResponseTarefaDTO;
import com.agendador.bff_agendador.controller.dto.tarefas.RequestTarefaDTO;
import com.agendador.bff_agendador.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

//Diferente do Usuario Cliente onde decidimos colocar o path em cada requisição, aqui no openFeign colocamos o parametro path, esse parametro atribui o path a todas as requisições dessa classe
@FeignClient(name = "tarefas", url = "${tarefa.url}", path="/tarefas")
public interface TarefaClient {

    @PostMapping
    ResponseTarefaDTO gravarTarefas(
            @RequestBody RequestTarefaDTO requestTarefaDTO,
            @RequestHeader("Authorization") String token);

    @GetMapping("/eventos")
    List<ResponseTarefaDTO> listarTarefasEntreDatas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataEventoInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataEventoFinal,
            @RequestHeader("Authorization") String token);

    @GetMapping
    List<ResponseTarefaDTO> buscarTarefasPeloEmail(
            @RequestHeader("Authorization") String token);

    @DeleteMapping
    Void deleteById(
            @RequestParam("id") String id,
            @RequestHeader("Authorization") String token);

    @PatchMapping("/status")
    ResponseTarefaDTO alteraStatusNotificacao(
            @RequestParam("status") StatusNotificacaoEnum status,
            @RequestParam("id") String id,
            @RequestHeader("Authorization") String token);

    @PutMapping("/updateTask")
    ResponseTarefaDTO alteraTarefas(
            @RequestBody RequestTarefaDTO requestTarefaDTO,
            @RequestParam("id") String id,
            @RequestHeader("Authorization") String token);
}
