//package com.bti.configs;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.annotation.PreDestroy;
//import org.jboss.logging.annotations.Pos;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.core.env.ConfigurableEnvironment;
//import org.springframework.core.env.MapPropertySource;
//import org.testcontainers.containers.BindMode;
////import org.testcontainers.containers.PostgreSQLContainer;
//
//import javax.sql.DataSource;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//@Configuration
//public class DatabaseConfig {
//
//    private static final String DATA_DIRECTORY = "./data/postgres";
////    private final PostgreSQLContainer postgres = new PostgreSQLContainer<>("postgres:16")
//    private final FixedPortPostgreSQLContainer postgres = new FixedPortPostgreSQLContainer("postgres:16")
//            .withDatabaseName("praetorius_db")
//            .withUsername("praetorius_admin")
//            .withPassword("passwd");
////            .withFileSystemBind(DATA_DIRECTORY, "/var/lib/postgresql/data", BindMode.READ_WRITE);
//
//
//
//    @PostConstruct
//    public void startContainer() {
////        ensureDataDirectory();
//        postgres.exposeFixedPort(5432, 5432);
//        postgres.start();
//    }
//
////    private void ensureDataDirectory() {
////        Path path = Paths.get(DATA_DIRECTORY);
////        if (!Files.exists(path)) {
////            try {
////                Files.createDirectories(path);
////            } catch (IOException e) {
////                throw new RuntimeException("Failed to create data directory: " + DATA_DIRECTORY, e);
////            }
////        }
////    }
//
//    @Bean
//    public DataSource dataSource() {
//        return DataSourceBuilder
//                .create()
//                .url(postgres.getJdbcUrl())
//                .username(postgres.getUsername())
//                .password(postgres.getPassword())
//                .driverClassName(postgres.getDriverClassName())
//                .build();
//    }
//
//    @PreDestroy
//    public void stopContainer() {
//        postgres.stop();
//    }
//}
