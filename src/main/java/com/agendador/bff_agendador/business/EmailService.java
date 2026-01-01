package com.agendador.bff_agendador.business;

import com.agendador.bff_agendador.controller.dto.notificacao.RequestNotificacaoTarefaDTO;
import com.agendador.bff_agendador.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//Classe service para envio de e-mail
@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public Void enviaEmail(RequestNotificacaoTarefaDTO requestNotificacaoTarefaDTO){
        return emailClient.enviarEmail(requestNotificacaoTarefaDTO);
    }

}
