package com.github.k9nz00.migration.factory;

import com.github.k9nz00.migration.configuration.properties.LiquibaseProperties;
import com.github.k9nz00.migration.model.LiquibaseCommand;
import com.github.k9nz00.migration.model.LiquibaseContext;
import liquibase.Liquibase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class LiquibaseContextFactoryImpl implements LiquibaseContextFactory {

    private static final DateTimeFormatter DATE_TO_ROLLBACK_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.SSS]");

    @Override
    public LiquibaseContext create(LiquibaseProperties properties, Liquibase liquibase) {
        try {
            LiquibaseCommand command = LiquibaseCommand.valueOf(properties.getCommand().toUpperCase());
            return LiquibaseContext.builder()
                    .command(command)
                    .liquibase(liquibase)
                    .tagToRollBackTo(properties.getTagToRollBackTo())
                    .dateToRollBackTo(getDateToRollBackTo(properties))
                    .releaseLocks(properties.isReleaseLocks())
                    .build();
        } catch (Exception e) {
            log.error("Ivalid executing command! Available next commands: {}", LiquibaseCommand.values(), e);
            throw e;
        }
    }

    private LocalDateTime getDateToRollBackTo(LiquibaseProperties properties) {
        return properties.getDateToRollBackTo() != null && !properties.getDateToRollBackTo().isEmpty() ?
                LocalDateTime.parse(properties.getDateToRollBackTo(), DATE_TO_ROLLBACK_FORMATTER) : null;
    }
}
