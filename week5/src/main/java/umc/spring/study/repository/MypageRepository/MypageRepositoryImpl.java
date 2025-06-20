package umc.spring.study.repository.MypageRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.QMember;
import umc.spring.study.domain.QStore;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MypageRepositoryImpl implements MypageRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    private final QMember member =  QMember.member;

    @Override
    public List<Member> selectMyInfo(Long baseMemberId) {
        return jpaQueryFactory
                .selectFrom(member)
                .where(member.id.eq(baseMemberId))
                .fetch();
    }

}
