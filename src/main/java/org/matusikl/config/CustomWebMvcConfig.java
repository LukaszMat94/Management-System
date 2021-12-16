package org.matusikl.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"org.matusikl.controller", "org.matusikl.controlleradvice"})
public class CustomWebMvcConfig implements WebMvcConfigurer {
}
