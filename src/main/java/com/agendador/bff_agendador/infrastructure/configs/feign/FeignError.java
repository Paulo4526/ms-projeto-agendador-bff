package com.agendador.bff_agendador.infrastructure.configs.feign;

import com.agendador.bff_agendador.infrastructure.exceptions.*;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()){
            case 409:
                return new ConflictException("Erro, atributo já existente!");

            case 404:
                return new ResourceNotFoundException("Erro, atributo não encontrado!");

            case 403:
                return new UnauthorizedException("Erro, usuário não autorizado!");

            case 401:
                return new UnauthorizedException("Erro, usuário informado não autorizado!");

            case 500:
                return new SeverException("Erro, tivemos um pequeno problema com o servidor!");

            default:
                return new BusinessException("Um erro inexperado aconteceu, por favor tente novamente!");
        }
    }
}
