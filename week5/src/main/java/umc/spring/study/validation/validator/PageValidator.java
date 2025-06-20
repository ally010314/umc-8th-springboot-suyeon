package umc.spring.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc.spring.study.validation.annotation.ValidPage;

public class PageValidator implements ConstraintValidator<ValidPage, Integer> {

    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext context) {
        return page != null && page > 0;
    }
}
