package com.agendador.bff_agendador.controller.dto.tarefas;

import com.agendador.bff_agendador.infrastructure.enums.StatusNotificacaoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestTarefaDTO {
    private String nomeTarefa;
    private String descricao;
    @Schema(
            type = "string",
            example = "02-01-2026 12:55:00",
            description = "Data no formato dd-MM-yyyy HH:mm:ss"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;
    @Schema(
            type = "string",
            example = "02-01-2026 12:55:00",
            description = "Data no formato dd-MM-yyyy HH:mm:ss"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
    private String emailUsuario;
    @Schema(
            type = "string",
            example = "02-01-2026 12:55:00",
            description = "Data no formato dd-MM-yyyy HH:mm:ss"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataAlteracao;
    private StatusNotificacaoEnum status;
}
