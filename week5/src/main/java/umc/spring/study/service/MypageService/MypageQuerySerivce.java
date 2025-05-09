package umc.spring.study.service.MypageService;

import umc.spring.study.domain.Member;

import java.util.List;

public interface MypageQuerySerivce {

    List<Member> selectMyInfo(Long baseMemberId);
}
