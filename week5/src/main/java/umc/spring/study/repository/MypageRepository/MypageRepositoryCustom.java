package umc.spring.study.repository.MypageRepository;

import umc.spring.study.domain.Member;

import java.util.List;

public interface MypageRepositoryCustom {
    List<Member> selectMyInfo(Long baseMemberId);
}
