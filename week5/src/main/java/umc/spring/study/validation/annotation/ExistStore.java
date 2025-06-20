package umc.spring.study.validation.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.study.validation.validator.CategoriesExistValidator;
import umc.spring.study.validation.validator.StoreExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StoreExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistStore {
    String message() default "존재하지 않는 가게입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
