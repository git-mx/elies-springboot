package com.elies.springboot.config;

import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 牟雪
 * @since 2018/4/12
 */

@Configuration
//使用SPRINGMVC的时候需要加上这个，使用SPRINGBOOT的时候最好把这个去掉，否则
//访问swagger-ui.html的时候会报404错误
//@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = { "com.elies.springboot.controller" })
public class SwaggerConfig {
    private final static String SERVICE_URL = "http://localhost:8080/";
    private final static String AUTHOR_NAME = "爱丽丝项目组";
    private final static String AUTHOR_EMAIL = "muxue@syswin.com";

    @Bean
    public Docket pcApi(){
        return createDocket("基础服务API", "基础服务API","/user/.*", "用户API")
                .globalOperationParameters(getPcParameter());
    }

    @Bean
    public Docket appApi(){
        return createDocket("基础服务API", "基础服务API","/test/.*", "测试API");
    }

    private Docket createDocket(String title, String description, String path, String groupName){
        ApiInfo apiInfo = new ApiInfo(
                title,
                description,
                "v1.0.0",
                SERVICE_URL,
                new Contact(AUTHOR_NAME, "", ""),
                "",
                "");
        Set<String> setProtocol = new HashSet<String>();
        setProtocol.add("http");
        Set<String> setProduce = new HashSet<String>();
        setProduce.add("application/json");
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex(path))
                .build()
                .groupName(groupName)
                .pathMapping("/")
                .enable(true)
                .apiInfo(apiInfo)
                .useDefaultResponseMessages(false)
                .protocols(setProtocol)
                .produces(setProduce);
    }

    private List<Parameter> getPcParameter(){
        ParameterBuilder builder = new ParameterBuilder();
        builder.name("sessionId")
                .description("PC端访问会话ID")
                .modelRef(new ModelRef("String"))
                .parameterType("header")
                .required(false);
        return Lists.newArrayList(builder.build());
    }
}
