package com.github.k9nz00.migration.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@ConfigurationProperties("liquibase")
@Component
@Data
public class LiquibaseProperties {
    private String profile;
    private String command;
    private String tagToRollBackTo;
    private String dateToRollBackTo;
    private String dbSchema;
    private Map<String, String> changeLog;
    private boolean releaseLocks;
}
