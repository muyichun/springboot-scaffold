package com.xujing.springbootscaffold.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author muyichun
 * @since 2020-1-11
 */
@Configuration
@EnableSwagger2
//@Profile({"dev", "test"})
public class SwaggerConfig {
    @Bean
    public Docket customImplementation() {
//        List<Parameter> pars = new ArrayList<Parameter>();
//        ParameterBuilder tokenPar = new ParameterBuilder();
//        tokenPar.name("token").description("用户token(可选)")
//                .modelRef(new ModelRef("string")).parameterType("header")
//                .required(false).build(); //header中的token参数非必填，传空也可以
//        pars.add(tokenPar.build());  //根据每个方法名也知道当前方法在设置什么参数
        return new Docket(DocumentationType.SWAGGER_2)
                .directModelSubstitute(LocalDateTime.class, String.class)
                .directModelSubstitute(LocalDate.class, String.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xujing.springbootscaffold.mp.controller"))
                .build()
//                .globalOperationParameters(pars)
                .apiInfo(testApiInfo());
    }

    /**
     * @return ApiInfo
     */
    private ApiInfo testApiInfo() {
        return new ApiInfoBuilder()
                .title("脚手架项目")
                .description("脚手架项目API")
                .version("v1.0")
                .termsOfServiceUrl("NO terms of server")
                .contact(new Contact("muyichun", "https://blog.csdn.net/First_sight","289651938@qq.com"))
                .build();
    }
}