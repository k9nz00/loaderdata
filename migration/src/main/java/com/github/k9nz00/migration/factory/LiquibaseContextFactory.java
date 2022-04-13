package com.github.k9nz00.migration.factory;

import com.github.k9nz00.migration.configuration.properties.LiquibaseProperties;
import com.github.k9nz00.migration.model.LiquibaseContext;
import liquibase.Liquibase;

public interface LiquibaseContextFactory {

    LiquibaseContext create(LiquibaseProperties properties, Liquibase liquibase);
}
