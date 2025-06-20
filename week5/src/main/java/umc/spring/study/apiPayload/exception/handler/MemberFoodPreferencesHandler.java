package umc.spring.study.apiPayload.exception.handler;

import umc.spring.study.apiPayload.code.BaseErrorCode;
import umc.spring.study.apiPayload.exception.GeneralException;

public class MemberFoodPreferencesHandler extends GeneralException {
    public MemberFoodPreferencesHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
