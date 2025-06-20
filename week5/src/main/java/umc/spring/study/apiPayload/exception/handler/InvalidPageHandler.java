package umc.spring.study.apiPayload.exception.handler;

import umc.spring.study.apiPayload.code.BaseErrorCode;
import umc.spring.study.apiPayload.exception.GeneralException;

public class InvalidPageHandler extends GeneralException {
    public InvalidPageHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}