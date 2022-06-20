package com.github.k9nz00.server.validation;

import com.github.k9nz00.server.validation.impl.CheckUserExistsValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CheckUserExistsValidatorImpl.class})
public @interface CheckUserExistsValidator {
    String message() default "Пользовать с таким логином уже существует. Используйте другой";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
