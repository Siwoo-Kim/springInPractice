package com.siwoo.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("com.siwoo.application.web")
public class WebConfig extends WebMvcConfigurationSupport{

    @Bean
    ViewResolver viewResolver(){
        return new InternalResourceViewResolver("/WEB-INF/views/",".jsp");
    }

}
