package com.projeto.finance_manager.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        // Fonte de configuração baseada na URL da aplicação
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // Criação da configuração de CORS
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("*")); // Permite requisições de qualquer origem (não recomendado em produção)
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Métodos HTTP permitidos
        config.setAllowedHeaders(List.of("Authorization", "Content-Type")); // Cabeçalhos permitidos

        // Aplica a configuração para todos os endpoints da aplicação
        source.registerCorsConfiguration("/**", config);

        // Retorna o filtro de CORS com a configuração aplicada
        return new CorsFilter();
    }
}