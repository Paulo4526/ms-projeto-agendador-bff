package com.agendador.bff_agendador.infrastructure.client;

import com.agendador.bff_agendador.controller.dto.notificacao.RequestNotificacaoTarefaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//Diferente do Usuario Cliente onde decidimos colocar o path em cada requisição, aqui no openFeign colocamos o parametro path, esse parametro atribui o path a todas as requisições dessa classe
@FeignClient(name = "notificacao", url = "${email.url}", path = "/email")
public interface EmailClient {

    @PostMapping
    Void enviarEmail(@RequestBody RequestNotificacaoTarefaDTO requestNotificacaoTarefaDTO);
}
