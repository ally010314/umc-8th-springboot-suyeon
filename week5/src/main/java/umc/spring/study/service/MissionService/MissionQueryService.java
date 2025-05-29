package umc.spring.study.service.MissionService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.enums.MissionStatus;
import umc.spring.study.domain.mapping.MemberMission;

import java.util.List;
import java.util.Optional;

public interface MissionQueryService {

    Page<MemberMission> findRunningMemberMissions(Long memberId, Pageable pageable);

}
