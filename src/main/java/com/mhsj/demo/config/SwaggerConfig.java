package com.mhsj.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
//@EnableWebMvc
//@EnableOpenApi
@EnableSwagger2 //开启Swagger2的自动配置
public class SwaggerConfig {

    @Bean
    public Docket docket(Environment environment) {
        // 设置要显示swagger的环境
//        Profiles of = Profiles.of("dev", "test");
        // 判断当前是否处于该环境
        // 通过 enable() 接收此参数判断是否要显示
//        boolean flag = environment.acceptsProfiles(of);
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
//                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mhsj.demo.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact DEFAULT_CONTACT = new Contact("蔡", "www", "123");
        return new ApiInfo("我的文档",
                "描述",
                "1.0",
                "urn:www.baidu.com", DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

}

