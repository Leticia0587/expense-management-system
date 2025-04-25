package com.projeto.finance_manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2) // Define que será usado o Swagger 2
                .select() // Inicia a seleção dos endpoints a serem documentados
                .apis(RequestHandlerSelectors.basePackage("com.projeto.finance_manager.controller")) // Limita a documentação às classes dentro do pacote de controllers
                .paths(PathSelectors.any()) // Documenta todos os caminhos disponíveis
                .build(); // Constrói e retorna o objeto Docket
    }
}