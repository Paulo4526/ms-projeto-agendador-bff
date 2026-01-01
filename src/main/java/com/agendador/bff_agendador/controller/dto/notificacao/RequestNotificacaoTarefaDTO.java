package com.agendador.bff_agendador.controller.dto.notificacao;

import com.agendador.bff_agendador.controller.dto.tarefas.ResponseTarefaDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestNotificacaoTarefaDTO {
    private String nomeTarefa;
    private String descricao;
    @Schema(
            type = "string",
            example = "02-01-2026 12:55:00",
            description = "Data no formato dd-MM-yyyy HH:mm:ss"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
    private String emailUsuario;

    public RequestNotificacaoTarefaDTO(ResponseTarefaDTO tarefa) {
        this(tarefa.getNomeTarefa(), tarefa.getDescricao(), tarefa.getDataEvento(), tarefa.getEmailUsuario());
    }
}

