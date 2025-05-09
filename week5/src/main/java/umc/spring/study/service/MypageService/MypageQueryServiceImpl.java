package umc.spring.study.service.MypageService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.domain.Member;
import umc.spring.study.repository.MypageRepository.MypageRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MypageQueryServiceImpl implements MypageQuerySerivce{

    private final MypageRepository mypageRepository;

    @Override
    public List<Member> selectMyInfo(Long baseMemberId) {
        return mypageRepository.selectMyInfo(baseMemberId);
    }
}
