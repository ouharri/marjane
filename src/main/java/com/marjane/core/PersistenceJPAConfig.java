package com.marjane.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Properties;

/**
 * Configuration class for JPA and database persistence.
 *
 * @author Ouharri Outman
 * @version 1.0
 */
@Configuration
@EnableTransactionManagement
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
@EnableJpaRepositories(basePackages = "com.marjane.Repositories")
public class PersistenceJPAConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.marjane.models");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dotenv.get("JDBC_DRIVER"));
        dataSource.setUrl(dotenv.get("DB_URL"));
        dataSource.setUsername(dotenv.get("DB_USERNAME"));
        dataSource.setPassword(dotenv.get("DB_PASSWORD"));
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean(name = "auditingDateTimeProvider")
    public DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(OffsetDateTime.now());
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url", dotenv.get("DB_URL"));
        properties.setProperty("hibernate.connection.username", dotenv.get("DB_USERNAME"));
        properties.setProperty("hibernate.connection.password", dotenv.get("DB_PASSWORD"));
        properties.setProperty("hibernate.connection.driver_class", dotenv.get("JDBC_DRIVER"));
        properties.setProperty("hibernate.dialect", dotenv.get("JPA_DIALECT"));
        properties.setProperty("hibernate.show_sql", dotenv.get("HIBERNATE_SHOW_SQL"));
        properties.setProperty("hibernate.current_session_context_class", dotenv.get("CURRENT_SESSION_CONTEXT_CLASS"));
        properties.setProperty("hibernate.hbm2ddl.auto", dotenv.get("HBM2DDL_AUTO"));
        return properties;
    }
}