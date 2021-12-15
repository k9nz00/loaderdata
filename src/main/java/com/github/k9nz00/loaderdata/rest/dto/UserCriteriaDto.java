package com.github.k9nz00.loaderdata.rest.dto;


import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserCriteriaDto extends TableCriteriaDto {
    @Parameter(description = "sorting column",
            schema = @Schema(type = "string", allowableValues = {"id", "roleId", "name"}, description = "defines the sorting order of users"))
    private final String sortColumn;
}
