package br.com.ivan.news.controller.adapter.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = CategoryValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CategoryConstraint {
    String message() default """
            Only the following categories are permitted: [        
                business,
                entertainment,
                general,
                health,
                science,
                sports,
                technology]
            """;
}
