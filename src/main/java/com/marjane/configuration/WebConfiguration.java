package com.marjane.configuration;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC and JPA/Hibernate configuration class.
 *
 * @author Ouharri Outman
 * @version 2.0
 */
@Configuration
@EnableWebMvc
@RequiredArgsConstructor
@EnableTransactionManagement
@PropertySource("classpath:/app.properties")
@ComponentScan(basePackages = {"com.marjane"})
@EnableJpaRepositories(basePackages = "com.marjane.Repositories")
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * Configuration of origins permissions
     *
     * @param registry - CORS registry
     */
    @Override
    public void addCorsMappings(@NotNull CorsRegistry registry) {
        registry.addMapping("/**").allowedHeaders("*").allowedMethods("*");
    }
}
