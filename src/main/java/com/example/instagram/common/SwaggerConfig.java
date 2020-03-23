package com.example.instagram.common;

import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket api() {
    Docket docket = new Docket(DocumentationType.SWAGGER_2)
        .pathMapping("/")
        .forCodeGeneration(true)
        .genericModelSubstitutes(ResponseEntity.class)
        .securityContexts(Lists.newArrayList(securityContext()))
        .securitySchemes(Lists.newArrayList(apiKey()))
        .useDefaultResponseMessages(false);

    docket = docket
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.ant("/**"))
        .build();
    return docket;
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(defaultAuth())
        .forPaths(PathSelectors.any())
        .build();
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEveryThing");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
  }

  private ApiKey apiKey() {
    return new ApiKey("JWT", CommonConstant.AUTHORIZATION, "header");
  }

}
