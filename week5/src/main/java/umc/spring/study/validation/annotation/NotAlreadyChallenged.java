package umc.spring.study.validation.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.study.validation.validator.NotAlreadyChallengedValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotAlreadyChallengedValidator.class)
@Target({ElementType.TYPE})  // DTO 전체 객체에 적용
@Retention(RetentionPolicy.RUNTIME)
public @interface NotAlreadyChallenged {
    String message() default "이미 도전 중인 미션입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}