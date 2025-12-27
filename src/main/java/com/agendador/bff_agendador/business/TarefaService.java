package com.agendador.bff_agendador.business;


import com.agendador.bff_agendador.controller.dto.tarefas.ShowTarefaDTO;
import com.agendador.bff_agendador.controller.dto.tarefas.TarefasDTO;
import com.agendador.bff_agendador.infrastructure.client.TarefaClient;
import com.agendador.bff_agendador.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaClient tarefaClient;

    //Metodo que irá gravar uma nova tarefa no MongoDB
    public ShowTarefaDTO gravarTarefa(TarefasDTO tarefasDTO, String token) {
        return tarefaClient.gravarTarefas(tarefasDTO, token);
    }

    //Metodo que retorna uma lista de tarefas entre datas
    public List<ShowTarefaDTO> buscarTarefasAgendadas(LocalDateTime dataEventoInicio, LocalDateTime dataEventoFinal, String token) {
        return tarefaClient.listarTarefasEntreDatas(dataEventoInicio, dataEventoFinal, token);
    }

    //Metodo que busca tarefas pelo email
    public List<ShowTarefaDTO> buscarTarefasPeloEmail(String token) {
        return tarefaClient.buscarTarefasPeloEmail(token);
    }

    //Metodo que deleta usuario pelo ID
    public Void deletaTarefaPorId(String id, String token) {
        return tarefaClient.deleteById(id, token);
    }

    //Metodo que atualiza status ta tarefa
    public ShowTarefaDTO alteraStatus(StatusNotificacaoEnum status, String id, String token) {
        return tarefaClient.alteraStatusNotificacao(status, id, token);
    }

    //Altera somente o que for passado no body da requisição, caso os outros sejam nulos não será feito a alteração
    public ShowTarefaDTO updateTarefas(TarefasDTO tarefasDTO, String id, String token) {
        return tarefaClient.alteraTarefas(tarefasDTO, id, token);
    }


}
