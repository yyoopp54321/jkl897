package com.example.jkl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Auther: szp
 * @Date: 2020/1/4 21:44
 * @Description:
 */
@Configuration
public class Swagger2 {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo( apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.jkl.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("uniapp练习")
                .description("简单优雅的restful风格，http://blog.csdn.net/****")
                .termsOfServiceUrl("http://blog.csdn.net/***")
                .version("1.0")
                .build();
    }
}