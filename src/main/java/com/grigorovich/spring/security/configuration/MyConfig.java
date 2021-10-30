package com.grigorovich.spring.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "com.grigorovich.spring.security")
@EnableWebMvc //эквивалентен <mvc:annotation-driven /> в XML. Он позволяет поддерживать @Controller -аннотированные классы, которые используют @RequestMapping для сопоставления входящих запросов с определенным методом.
public class MyConfig {

    @Bean
    public ViewResolver viewResolver() { //вместо конфигурации в XML файле
        InternalResourceViewResolver viewResolver=
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
