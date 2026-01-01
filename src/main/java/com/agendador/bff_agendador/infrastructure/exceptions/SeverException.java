package com.agendador.bff_agendador.infrastructure.exceptions;

public class SeverException extends RuntimeException{

    public SeverException(String message){
        super(message);
    }

    public SeverException(String message, Throwable throwable){
        super(message, throwable);
    }
}
