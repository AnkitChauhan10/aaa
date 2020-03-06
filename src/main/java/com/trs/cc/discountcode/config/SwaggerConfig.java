package com.trs.cc.discountcode.config;


import com.google.common.collect.Lists;
import com.trs.cc.discountcode.utils.CustomHTTPHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket eventApi(){
        return new Docket(
                DocumentationType.SWAGGER_2)
                .globalOperationParameters(getParameters())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.trs.cc.discountcode.controller"))
                .paths(PathSelectors.regex("/*.*"))
                .build()
                .pathProvider(new ExtendRelativePathProvider())
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()))
                .apiInfo(apiEndPointsInfo());
    }

    private List<Parameter> getParameters(){
        return new ArrayList<>();
    }

    private ApiKey apiKey() {
        return new ApiKey(CustomHTTPHeaders.TOKEN.toString(), CustomHTTPHeaders.TOKEN.toString(), "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/*.*"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference(CustomHTTPHeaders.TOKEN.toString(), authorizationScopes));
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder()
                .version("1.0.0")
                .build();
    }

}
