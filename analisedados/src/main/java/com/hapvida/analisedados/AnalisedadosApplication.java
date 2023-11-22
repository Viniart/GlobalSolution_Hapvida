package com.hapvida.analisedados;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SecurityScheme(
//		name = "bearerAuth",
//		type = SecuritySchemeType.HTTP,
//		bearerFormat = "JWT",
//		scheme = "bearer"
//)
@OpenAPIDefinition(
		info = @Info(
				title = "Documentação Da API de Análise de Dados e Log",
				description = "API de Análise de Dados e Log",
				version = "v1.0",
				contact = @Contact(
						name = "Grupo GlobalSolution",
						email = "teste@fiap.com.br"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.javaguides.net/license"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Link de Apoio",
				url = "https://tjf.totvs.com.br/docs/open-api"
		)
)
public class AnalisedadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalisedadosApplication.class, args);
	}

}
