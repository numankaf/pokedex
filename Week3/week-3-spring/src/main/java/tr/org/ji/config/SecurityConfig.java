package tr.org.ji.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import tr.org.ji.service.impl.UserServiceImpl;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    private static final int SC_FORBIDDEN = 403 ;
    private static final int SC_UNAUTHORIZED = 401;
    private static final int SC_OK =200 ;
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
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
    public DaoAuthenticationProvider authenticationProvider() {
        var daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(customizer -> //
                        customizer.requestMatchers(AUTH_WHITELIST).anonymous() //
                                .requestMatchers(HttpMethod.POST, "/users").permitAll() //
                                .anyRequest().hasAnyRole("ADMIN", "USER")) //
                .exceptionHandling(customizer -> customizer.accessDeniedHandler( //
                                (req, resp, ex) -> resp.setStatus(SC_FORBIDDEN)) // if someone tries to access protected resource but doesn't have enough permissions
                        .authenticationEntryPoint((req, resp, ex) -> resp.setStatus(SC_UNAUTHORIZED))) //
                .sessionManagement(customizer -> customizer.invalidSessionStrategy((req, resp) -> resp.setStatus(SC_UNAUTHORIZED))) //
                .formLogin(customizer -> customizer.loginProcessingUrl("/login") //
                        .successHandler((req, resp, auth) -> resp.setStatus(SC_OK)) // success authentication).and().formLogin()
                        .failureHandler((req, resp, ex) -> resp.setStatus(SC_FORBIDDEN))) //  bad credentials
                .logout(customizer -> customizer.logoutUrl("/logout") //
                        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())) //
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}