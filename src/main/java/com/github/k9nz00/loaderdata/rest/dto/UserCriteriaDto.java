package com.github.k9nz00.loaderdata.rest.dto;


import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Valid
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCriteriaDto extends TableCriteriaDto {
    @Parameter(description = "sorting column",
            schema = @Schema(type = "string", allowableValues = {"id", "roleId", "name"}, description = "defines the sorting order of users"))
    private String sortColumn;

    @Parameter(
            name = "условие выборки пользователей",
            description = "Определяет логику выбортки пользователей." +
                    " Если оставить пустым, то будут выбраны все пользователи." +
                    " Если true -  будут выбрагы только активные(неудаленные) пользователи." +
                    " Если указать false - выберутся только удаленные(деактивированные)",
            schema = @Schema(type = "boolean", allowableValues = {"true", "false"})
    )
    private Boolean active;
}
