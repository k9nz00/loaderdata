package com.github.k9nz00.server.validation.impl;

import com.github.k9nz00.server.dao.UserDao;
import com.github.k9nz00.server.validation.CheckUserExistsValidator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckUserExistsValidatorImpl implements ConstraintValidator<CheckUserExistsValidator, String> {

    private final UserDao userDao;

    @Autowired
    public CheckUserExistsValidatorImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        boolean userExists = userDao.userExists(username);
        if (userExists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Пользовать с таким логином уже существует. Используйте другой login")
                    .addConstraintViolation();
            return false;
        } else {
            return true;
        }
    }
}
