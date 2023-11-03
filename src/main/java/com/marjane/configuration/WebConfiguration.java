package com.marjane.configuration;

import com.marjane.Providers.HibernatePersistenceProvider;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC and JPA/Hibernate configuration class.
 *
 * @author Maksym Panov
 * @version 2.0
 */
@Configuration
@EnableWebMvc
@RequiredArgsConstructor
@ComponentScan(basePackages = {"com.marjane"})
public class WebConfiguration implements WebMvcConfigurer {
    private final PersistenceUnitConfig persistenceUnitInfo;
    private final HibernatePersistenceProvider hibernatePersistenceProvider;

    /**
     * Configuration of origins permissions
     *
     * @param registry - CORS registry
     */
    @Override
    public void addCorsMappings(@NotNull CorsRegistry registry) {
        registry.addMapping("/**").allowedHeaders("*").allowedMethods("*");
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return hibernatePersistenceProvider
                .createContainerEntityManagerFactory(
                        persistenceUnitInfo,
                        persistenceUnitInfo.getProperties()
                );
    }
}
