package com.pokedex.apigateway.filter;


import com.pokedex.apigateway.config.JwtTokenProvider;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final RouteValidator routeValidator;
    private final JwtTokenProvider tokenProvider;

    public AuthenticationFilter(RouteValidator routeValidator, JwtTokenProvider tokenProvider) {
        super(Config.class);
        this.routeValidator = routeValidator;
        this.tokenProvider = tokenProvider;
    }


    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if(routeValidator.isSecured.test(exchange.getRequest())){
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("Authorization header is missing!");
                }
            }
            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            if(authHeader!=null && authHeader.startsWith("Bearer ")){
                authHeader = authHeader.substring(7);
            }

            if (!tokenProvider.validateToken(authHeader)){
                throw new RuntimeException("Unauthorized access");
            }

            List<String> roles = Arrays.asList(config.getRole().split(";"));
            if(!roles.contains(tokenProvider.getRoleFromToken(authHeader))){
                var response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
            return chain.filter(exchange);
        }));
    }


    public static class Config{
        private String role;

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("role");
    }

}
