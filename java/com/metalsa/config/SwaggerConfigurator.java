package com.metalsa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author  Edgar Leal
 * 
 * 
 * 
 */
@Configuration
@EnableSwagger2
public class SwaggerConfigurator {
	@Bean
	public Docket catalogAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
					.select()
						.apis( RequestHandlerSelectors.basePackage("com.metalsa.core.api") )
				.build();

	}

	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Metalsa Core API")
                .description("Metalsa Core API Definition")
                .termsOfServiceUrl("/docs/license.html")
                .license("Private License")
                .licenseUrl("/docs/license.html")
                .version("1.0")
                .build();
	}

}
