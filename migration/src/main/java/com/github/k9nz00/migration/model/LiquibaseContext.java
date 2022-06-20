package com.github.k9nz00.migration.model;

import liquibase.Liquibase;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@Builder
public class LiquibaseContext {
    @NonNull
    private LiquibaseCommand command;
    private String tagToRollBackTo;
    private LocalDateTime dateToRollBackTo;
    @NonNull
    private Liquibase liquibase;
    private boolean releaseLocks;
}
