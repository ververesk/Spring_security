package com.grigorovich.spring.security.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@ComponentScan(basePackages = "com.grigorovich.spring.security")
@EnableWebMvc //эквивалентен <mvc:annotation-driven /> в XML. Он позволяет поддерживать @Controller -аннотированные классы, которые используют @RequestMapping для сопоставления входящих запросов с определенным методом.
public class MyConfig {

    @Bean
    public ViewResolver viewResolver() { //вместо конфигурации в XML файле
        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

        @Bean
        public DataSource dataSource() { //настрваиваем пожключение к базе данных
            ComboPooledDataSource dataSource=new ComboPooledDataSource();
            try {
                dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
                dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/my_db?useSSL=false&serverTimezone=UTC"); //обратить на это внимание очень важно
                dataSource.setUser("bestuser");
                dataSource.setPassword("bestuser");
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
            return dataSource;
        }
    }

