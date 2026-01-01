package com.agendador.bff_agendador.infrastructure.configs.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    //Anotação bean para introduzir o metodo abaixo no contexto do spring
    @Bean
    public FeignError feignError(){
        return new FeignError();
    }
}
