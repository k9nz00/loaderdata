package com.github.k9nz00.migration.configuration.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("")
@Component
@Data
@ToString
public class DatasourceProperties {
    private String driverClassName;
    private String jdbcUrl;
    private String database;
    private String password;
    private String username;
    private Integer maxPoolSize;
    private String poolName;
}


