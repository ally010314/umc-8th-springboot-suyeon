package umc.spring.study.apiPayload.exception.handler;

import umc.spring.study.apiPayload.code.BaseErrorCode;
import umc.spring.study.apiPayload.exception.GeneralException;

public class ReviewHandler extends GeneralException {
    public ReviewHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
