package com.example.demo.validator;

import com.example.demo.entity.User;
import com.example.demo.validator.annotation.ValidUserId;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Valid;

import java.util.concurrent.ConcurrentMap;

public class ValidUserIdValidator implements ConstraintValidator<ValidUserId, User> {
    @Override
    public boolean isValid(User user, ConstraintValidatorContext context){
        if(user ==null)
            return true;
        return user.getId()!=null;
    }
}
