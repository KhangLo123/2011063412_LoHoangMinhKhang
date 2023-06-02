package com.example.demo.validator;

import com.example.demo.entity.PhongBan;

import com.example.demo.validator.annotation.ValidCategoryId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPhongBanIdValidator implements ConstraintValidator<ValidCategoryId, PhongBan> {
    @Override
    public boolean isValid(PhongBan category, ConstraintValidatorContext context){
        return category !=null && category.getId() !=null;
    }

}
