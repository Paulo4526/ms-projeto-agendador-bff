package com.agendador.bff_agendador.infrastructure.configs.cors;

import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer configCors(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        //Porta do front-end permitido
                        .allowedOrigins("http://localhost:3000")
                        //Permitindo os metodos a ser requisitados pelo front-end
                        .allowedMethods("GET", "POST", "PATCH", "DELETE", "PUT")
                        //Permitindo qualquer header passado pelo front
                        .allowedHeaders("*")
                        //Permitindo as credenciais
                        .allowCredentials(true)
                        .maxAge(360);
            }
        };
    }
}
