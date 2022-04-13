package com.github.k9nz00.migration.service;

import com.github.k9nz00.migration.model.LiquibaseCommand;
import com.github.k9nz00.migration.model.LiquibaseContext;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;

import java.io.IOException;

public interface LiquibaseCommandExecutor {

    void execute(SpringLiquibase liquibase, LiquibaseContext context) throws LiquibaseException, IOException;

    LiquibaseCommand getCommand();
}
