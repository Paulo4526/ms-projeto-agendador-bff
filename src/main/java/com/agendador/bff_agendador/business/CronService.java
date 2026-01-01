package com.agendador.bff_agendador.business;

import com.agendador.bff_agendador.controller.dto.login.RequestLoginDTO;
import com.agendador.bff_agendador.controller.dto.login.ResponseTokenDTO;
import com.agendador.bff_agendador.controller.dto.notificacao.RequestNotificacaoTarefaDTO;
import com.agendador.bff_agendador.controller.dto.tarefas.ResponseTarefaDTO;
import com.agendador.bff_agendador.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {
    //Tarefa de cronometro que a cada 5 minutos fará uma requisição ao banco, onde verificará quais tarefas devem ser lembradas
    private final TarefaService tarefaService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${login.email}")
    private String email;
    @Value("${login.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscarTarefasProximaHora(){
        ResponseTokenDTO response = login(converterParaRequestDTO());
        String token ="Bearer " + response.getToken();
        log.info("Iniciada a verificação de tarefas em " + LocalDateTime.now());
        LocalDateTime hora = LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(1);
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(1).plusMinutes(5);
        List<ResponseTarefaDTO> listaTarefas = tarefaService.buscarTarefasAgendadas(hora, horaFuturaMaisCinco, token);
        log.info("Tarefas encontradas: " + listaTarefas);
        listaTarefas.forEach(tarefa -> {
            emailService.enviaEmail(new RequestNotificacaoTarefaDTO(tarefa));
            tarefaService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(), token);
            log.info("Email enviado para: " + tarefa.getEmailUsuario());
        });
        log.info("Verificação finalizada em: " + LocalDateTime.now());
    }

    //Criando o metodo que faz login automatico no sistema
    public ResponseTokenDTO login(RequestLoginDTO login){
        return usuarioService.login(login);
    }

    //Passando os valores estáticos e convertendo para o RequestLogin
    public RequestLoginDTO converterParaRequestDTO(){
        return RequestLoginDTO.builder()
                .email(email)
                .senha(senha)
                .build();
    }

}
