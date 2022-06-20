package com.github.k9nz00.migration.configuration;

import com.github.k9nz00.migration.configuration.properties.DatasourceProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Slf4j
@Profile("default")
public class MigrationConfiguration {

    @Bean
    public DataSource hikariDatasource(DatasourceProperties properties) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(properties.getDriverClassName());
        config.setJdbcUrl(properties.getJdbcUrl());
        config.setUsername(properties.getLogin());
        config.setPassword(properties.getPassword());
        config.setSchema(properties.getDatabase());
        config.setMaximumPoolSize(properties.getMaxPoolSize());
        config.setConnectionTestQuery("SELECT 1");
        config.setPoolName(properties.getPoolName());
        return new HikariDataSource(config);
    }
}
