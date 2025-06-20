package umc.spring.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.study.apiPayload.code.status.ErrorStatus;
import umc.spring.study.repository.StoreRepository.StoreRepository;
import umc.spring.study.validation.annotation.ExistStore;

@Component
@RequiredArgsConstructor
public class StoreExistValidator implements ConstraintValidator<ExistStore, Long> {

    private final StoreRepository storeRepository;

    @Override
    public boolean isValid(Long storeId, ConstraintValidatorContext context) {
        if (storeId == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    ErrorStatus.REVIEW_STORE_NOT_FOUND.toString()
            ).addConstraintViolation();
            return false;
        }

        boolean isValid = storeRepository.existsById(storeId);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    ErrorStatus.REVIEW_STORE_NOT_FOUND.toString()
            ).addConstraintViolation();
        }

        return isValid;
    }
}

