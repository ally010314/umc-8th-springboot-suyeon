package umc.spring.study.service.MissionService;

import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.Review;
import umc.spring.study.domain.mapping.MemberMission;
import umc.spring.study.web.dto.MemberRequestDTO;
import umc.spring.study.web.dto.MissionRequestDTO;
import umc.spring.study.web.dto.ReviewRequestDTO;

public interface MissionCommandService {
    Mission createMission(MissionRequestDTO.CreateMissionDTO request);

    @Transactional
    MemberMission challengeMission(MissionRequestDTO.ChallengeMissionRequestDTO request);

    @Transactional
    Member joinMember(MemberRequestDTO.JoinDto request);
}
