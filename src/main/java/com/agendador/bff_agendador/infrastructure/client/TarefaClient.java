package com.agendador.bff_agendador.infrastructure.client;

import com.agendador.bff_agendador.controller.dto.tarefas.ShowTarefaDTO;
import com.agendador.bff_agendador.controller.dto.tarefas.TarefasDTO;
import com.agendador.bff_agendador.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "tarefas", url = "${tarefa.url}")
public interface TarefaClient {

    @PostMapping
    ShowTarefaDTO gravarTarefas(
            @RequestBody TarefasDTO tarefasDTO,
            @RequestHeader("Authorization") String token);

    @GetMapping("/eventos")
    List<ShowTarefaDTO> listarTarefasEntreDatas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataEventoInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataEventoFinal,
            @RequestHeader("Authorization") String token);

    @GetMapping
    List<ShowTarefaDTO> buscarTarefasPeloEmail(
            @RequestHeader("Authorization") String token);

    @DeleteMapping
    Void deleteById(
            @RequestParam("id") String id,
            @RequestHeader("Authorization") String token);

    @PatchMapping("/status")
    ShowTarefaDTO alteraStatusNotificacao(
            @RequestParam("status") StatusNotificacaoEnum status,
            @RequestParam("id") String id,
            @RequestHeader("Authorization") String token);

    @PutMapping("/updateTask")
    ShowTarefaDTO alteraTarefas(
            @RequestBody TarefasDTO tarefasDTO,
            @RequestParam("id") String id,
            @RequestHeader("Authorization") String token);
}
