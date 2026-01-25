package br.com.kubo.authapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration // classe de configuração
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{ //criando classe com filtro de 
        http.csrf( (csrf) -> {
                    csrf.disable();
                })
                .authorizeHttpRequests((auth) -> {
                    auth.requestMatchers(new AntPathRequestMatcher("/open","GET")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/users","POST")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/login","POST")).permitAll()
                            .anyRequest().authenticated();
                })
                .addFilterBefore(new AuthFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

}