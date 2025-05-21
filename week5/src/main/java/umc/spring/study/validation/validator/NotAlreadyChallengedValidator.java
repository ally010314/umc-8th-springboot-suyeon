package umc.spring.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.study.apiPayload.code.status.ErrorStatus;
import umc.spring.study.domain.enums.MissionStatus;
import umc.spring.study.repository.MemberMissionRepository.MemberMissionRepository;
import umc.spring.study.validation.annotation.NotAlreadyChallenged;

@Component
@RequiredArgsConstructor
public class NotAlreadyChallengedValidator implements ConstraintValidator<NotAlreadyChallenged, Long> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public boolean isValid(Long memberMissionId, ConstraintValidatorContext context) {
        if (memberMissionId == null) return true;
        boolean isValid = memberMissionRepository.findById(memberMissionId)
                .map(m -> m.getStatus() == MissionStatus.RUNNING)
                .orElse(false);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                  ErrorStatus.MISSION_ALREADY_RUNNING.getMessage()
            ).addConstraintViolation();
        }

        return isValid;
    }
}
