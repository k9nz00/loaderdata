package com.github.k9nz00.migration.service.command;

import com.github.k9nz00.migration.model.LiquibaseCommand;
import com.github.k9nz00.migration.model.LiquibaseContext;
import com.github.k9nz00.migration.service.LiquibaseCommandExecutor;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;

@Service
public class RollbackExecutor implements LiquibaseCommandExecutor {

    @SneakyThrows
    @Override
    public void execute(SpringLiquibase springLiquibase, LiquibaseContext context) throws LiquibaseException {
        Liquibase liquibase = context.getLiquibase();
        springLiquibase.setTag(context.getTagToRollBackTo());
        if (context.getTagToRollBackTo() == null && context.getDateToRollBackTo() == null) {
            throw new LiquibaseException("Tag or date time for rolling back can't be null");
        } else if (springLiquibase.getTag() != null) {
            liquibase.rollback(springLiquibase.getTag(), new Contexts(springLiquibase.getContexts()));
        } else {
            Date rollbackToDate = Date.from(context.getDateToRollBackTo().atZone(ZoneId.systemDefault()).toInstant());
            liquibase.rollback(rollbackToDate, new Contexts(springLiquibase.getContexts()), new LabelExpression());
        }
    }

    @Override
    public LiquibaseCommand getCommand() {
        return LiquibaseCommand.ROLLBACK;
    }
}
