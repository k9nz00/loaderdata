package com.github.k9nz00.migration.service.command;

import com.github.k9nz00.migration.model.LiquibaseCommand;
import com.github.k9nz00.migration.model.LiquibaseContext;
import com.github.k9nz00.migration.service.LiquibaseCommandExecutor;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.stereotype.Service;

@Service
public class UpdateExecutor implements LiquibaseCommandExecutor {

    @Override
    public void execute(SpringLiquibase springLiquibase, LiquibaseContext context) throws LiquibaseException {
        Liquibase liquibase = context.getLiquibase();
        if (springLiquibase.isClearCheckSums()) {
            liquibase.clearCheckSums();
        }
        if (springLiquibase.isTestRollbackOnUpdate()) {
            if (springLiquibase.getTag() != null) {
                liquibase.updateTestingRollback(springLiquibase.getTag(), new Contexts(springLiquibase.getContexts()), new LabelExpression(springLiquibase.getLabels()));
            } else {
                liquibase.updateTestingRollback(new Contexts(springLiquibase.getContexts()), new LabelExpression(springLiquibase.getLabels()));
            }
        } else if (springLiquibase.getTag() != null) {
            liquibase.update(springLiquibase.getTag(), new Contexts(springLiquibase.getContexts()), new LabelExpression(springLiquibase.getLabels()));
        } else {
            liquibase.update(new Contexts(springLiquibase.getContexts()), new LabelExpression(springLiquibase.getLabels()));
        }
    }

    @Override
    public LiquibaseCommand getCommand() {
        return LiquibaseCommand.UPDATE;
    }
}
