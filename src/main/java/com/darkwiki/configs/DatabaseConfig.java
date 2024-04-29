package com.darkwiki.configs;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    private FixedPortPostgreSQLContainer postgres = new FixedPortPostgreSQLContainer("postgres:latest")
            .withDatabaseName("darkwiki_db")
            .withUsername("darkwiki_admin")
            .withPassword("passwd");

    @PostConstruct
    public void startContainer() {
        postgres.exposeFixedPort(5432, 5432);
        postgres.start();
    }

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .url(postgres.getJdbcUrl())
                .username(postgres.getUsername())
                .password(postgres.getPassword())
                .driverClassName(postgres.getDriverClassName())
                .build();
    }

    @PreDestroy
    public void stopContainer() {
        postgres.stop();
    }
}
