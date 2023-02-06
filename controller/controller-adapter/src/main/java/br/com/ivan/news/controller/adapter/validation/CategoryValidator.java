package br.com.ivan.news.controller.adapter.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class CategoryValidator implements ConstraintValidator<CategoryConstraint, String> {

    final List<String> allowedValues = List.of(
            "business", "entertainment", "general",
            "health", "science", "sports", "technology"
    );


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return allowedValues.contains(value);
    }
}
