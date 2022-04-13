package com.github.k9nz00.migration.service;

import com.github.k9nz00.migration.configuration.properties.LiquibaseProperties;
import com.github.k9nz00.migration.factory.LiquibaseContextFactory;
import com.github.k9nz00.migration.model.LiquibaseCommand;
import com.github.k9nz00.migration.model.LiquibaseContext;
import liquibase.Liquibase;
import liquibase.integration.spring.SpringLiquibase;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class LiquibaseService extends SpringLiquibase {

    private final Map<LiquibaseCommand, LiquibaseCommandExecutor> executorMap;
    private final LiquibaseContextFactory liquibaseContextFactory;
    private final LiquibaseProperties liquibaseProperties;

    @Autowired
    public LiquibaseService(DataSource dataSource,
                            @Value("${spring.liquibase.enabled}") boolean isEnabled,
                            LiquibaseContextFactory liquibaseContextFactory,
                            List<LiquibaseCommandExecutor> executors,
                            LiquibaseProperties liquibaseProperties) {
        this.liquibaseProperties = liquibaseProperties;
        this.setDataSource(dataSource);
        this.setDefaultSchema(liquibaseProperties.getDbSchema());
        this.setChangeLog(liquibaseProperties.getChangeLog().get("database"));
        this.setShouldRun(isEnabled);
        this.executorMap = executors.stream().collect(Collectors.toMap(LiquibaseCommandExecutor::getCommand, it -> it));
        this.liquibaseContextFactory = liquibaseContextFactory;
    }

    @SneakyThrows
    @Override
    protected void performUpdate(Liquibase liquibase) {
        LiquibaseContext context = liquibaseContextFactory.create(liquibaseProperties, liquibase);
        if (context.isReleaseLocks()) {
            liquibase.forceReleaseLocks();
        }
        executorMap.get(context.getCommand()).execute(this, context);
    }

}
