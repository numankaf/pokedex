package com.pokedex.authservice.config;

import com.pokedex.authservice.auth.JwtAuthenticationEntryPoint;
import com.pokedex.authservice.auth.JwtAuthenticationFilter;
import com.pokedex.authservice.auth.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final JwtTokenProvider tokenProvider;
    private final UserDetailsService userDetailsService;

    private final JwtAuthenticationEntryPoint handler;

    public SecurityConfig(JwtTokenProvider tokenProvider, UserDetailsService userDetailsService, JwtAuthenticationEntryPoint handler) {
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
        this.handler = handler;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(tokenProvider, userDetailsService);
    }


    private static final String[] AUTH_WHITELIST = { //
            "/api/v1/h2-console", //
            "/api/v1/h2-console/**", //
            "/v3/api-docs/**", //
            "/swagger-ui/**", //
            "/swagger-ui.html", //
            "/swagger-ui/index.html", //
            "/api/v1/webjars/**", //
            "/api/v1/graphiql", //
            "/api/v1/api/graphql", //
    };


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers(h -> h.frameOptions(f -> f.disable()));
        return http
                .csrf(csrf -> csrf.disable()).cors(cors->cors.disable()).
                cors(customizer -> customizer
                        .configurationSource(request -> {
                            CorsConfiguration config = new CorsConfiguration();
                            config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                            config.setAllowedMethods(Collections.singletonList("*"));
                            config.setAllowedHeaders(Collections.singletonList("*"));
                            config.setAllowCredentials(true);
                            config.setMaxAge(3600L);
                            return config;
                        })).exceptionHandling(t -> t.authenticationEntryPoint(handler))
                .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.requestMatchers(AUTH_WHITELIST).anonymous()
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();


    }

    @Configuration
    @EnableWebMvc
    public static class WebConfig implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry corsRegistry) {
            corsRegistry.addMapping("/**")
                    .allowedOrigins("http://localhost:3000/","http://localhost:4200")
                    .allowedMethods("*")
                    .maxAge(3600L)
                    .allowedHeaders("*")
                    .exposedHeaders("Authorization")
                    .allowCredentials(true);
        }

    }
}