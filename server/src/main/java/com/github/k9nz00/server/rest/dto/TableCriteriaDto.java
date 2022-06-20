package com.github.k9nz00.server.rest.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

@Data
public abstract class TableCriteriaDto {
    @Parameter(description = "Result size limit. Maximum 250")
    @Max(250)
    private Integer limit;

    private Integer offset;

    @Parameter(description = "Sorting direction", schema = @Schema(allowableValues = {"ASC", "DESC"}))
    @Pattern(regexp = "^ASC|DESC|asc|desc", message = "allowable values = [ASC, DESC]")
    private String sortDirection;

    public abstract String getSortColumn();
}
