package com.pokedex.apigateway.exception;

public class PokedexGatewayAuthException extends RuntimeException{
    public PokedexGatewayAuthException(String message){
        super(message);
    }
}
