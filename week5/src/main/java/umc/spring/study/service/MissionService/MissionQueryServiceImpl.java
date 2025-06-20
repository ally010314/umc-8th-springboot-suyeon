package umc.spring.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.enums.MissionStatus;
import umc.spring.study.domain.mapping.MemberMission;
import umc.spring.study.repository.MemberMissionRepository.MemberMissionRepository;
import umc.spring.study.repository.MissionRepository.MissionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Page<MemberMission> findRunningMemberMissions(Long memberId, Pageable pageable) {
        return memberMissionRepository.findByMemberIdAndStatus(memberId, MissionStatus.RUNNING, pageable);
    }
}