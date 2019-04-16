package com.beerhouse.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("BeerHouse API")
        .description("")
        .license("")
        .licenseUrl("")
        .termsOfServiceUrl("")
        .version("v1")
        .contact(new Contact("Aléxis Kiosia", "", "alexis.kiosia@gmail.com"))
        .build();
  }

  @Bean
  public Docket customImplementation() {
    return new Docket(DocumentationType.SPRING_WEB)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.beerhouse.controller"))
        .build()
        .apiInfo(apiInfo());
  }
}