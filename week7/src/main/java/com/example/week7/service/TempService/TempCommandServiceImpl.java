package com.example.week7.service.TempService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.week7.apiPayload.code.status.ErrorStatus;
import com.example.week7.apiPayload.exception.handler.TempHandler;

@Service
@RequiredArgsConstructor
public class TempCommandServiceImpl implements TempQueryService
{
    @Override
    public void CheckFlag(Integer flag) {
        if (flag == 1)
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);

    }
}
