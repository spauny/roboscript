package com.spauny.joy.roboscript;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableAsync
@EnableScheduling
@ComponentScan({"com.spauny.joy.roboscript"})
@EnableWebMvc
public class WebAppConfiguration extends WebMvcConfigurerAdapter {
    public WebAppConfiguration() {
        super();
    }
}
