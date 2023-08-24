package com.pokedex.apigateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    private RouteValidator() {
    }

    private static final List<String> openEndpoints = List.of("/pokedex/auth/**");


    public static final Predicate<ServerHttpRequest> isSecured =
            request -> openEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));


}
