package umc.spring.study.repository.MissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.QMember;
import umc.spring.study.domain.QMission;
import umc.spring.study.domain.QStore;
import umc.spring.study.domain.enums.MissionStatus;
import umc.spring.study.domain.mapping.QMemberMission;

import java.time.LocalDateTime;
import java.util.List;



@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Mission> findRunningMissions(Long baseMissionId, int limit) {
        return findMissionsByStatus(MissionStatus.RUNNING, baseMissionId, limit);
    }

    @Override
    public List<Mission> findFinishedMissions(Long baseMissionId, int limit) {
        return findMissionsByStatus(MissionStatus.FINISHED, baseMissionId, limit);
    }

    private List<Mission> findMissionsByStatus(MissionStatus status, Long baseMissionId, int limit) {
        QMember member = QMember.member;
        QMemberMission memberMission = QMemberMission.memberMission;
        QMission mission = QMission.mission;
        QStore store = QStore.store;

        BooleanBuilder builder = new BooleanBuilder(); //조건이 필수가 아닐 때 BooleanBuilder 사용 (동적 조건)

        if (baseMissionId != null) {
            builder.and(mission.createdAt.lt(
                    JPAExpressions.select(mission.createdAt)
                            .from(mission)
                            .where(mission.id.eq(baseMissionId)) // JPAExpressions : QueryDSL에서 subquery 만들기 위한 클래스
            ));
        } // BaseMissionId의 미션보다 생성 시각이 이전인 미션만 조회하기

        return queryFactory
                .select(mission)
                .from(member)
                .join(memberMission).on(member.eq(memberMission.member))
                .join(mission).on(memberMission.mission.eq(mission))
                .join(store).on(memberMission.store.eq(store))
                .where(
                        builder,
                        memberMission.status.eq(status)
                )
                .orderBy(mission.createdAt.desc())
                .limit(limit)
                .fetch();
    }

    @Override
    public List<Mission> findAvailableMissionsByRegion(String region, Long baseMissionId, int limit) {
        QStore store = QStore.store;
        QMission mission = QMission.mission;
        QMemberMission memberMission = QMemberMission.memberMission;

        return queryFactory
                .select(mission)
                .from(store)
                .leftJoin(mission).on(mission.store.eq(store))
                .leftJoin(memberMission).on(memberMission.mission.eq(mission))
                .where(
                        store.region.eq(region),
                        mission.deadline.gt(LocalDateTime.now()),
                        memberMission.status.eq(MissionStatus.BEFORE),
                        mission.deadline.gt(
                                JPAExpressions.select(QMission.mission.deadline)
                                        .from(QMission.mission)
                                        .where(QMission.mission.id.eq(baseMissionId))
                        )
                )
                .orderBy(mission.deadline.asc())
                .limit(limit)
                .fetch();
    }

}
