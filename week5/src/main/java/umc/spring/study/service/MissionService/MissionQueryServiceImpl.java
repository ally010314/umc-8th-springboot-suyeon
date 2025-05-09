package umc.spring.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.enums.MissionStatus;
import umc.spring.study.repository.MissionRepository.MissionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Override
    public List<Mission> findRunningMissions(Long baseMissionId, int limit) {
        return missionRepository.findRunningMissions(baseMissionId, limit);
    }

    @Override
    public List<Mission> findFinishedMissions(Long baseMissionId, int limit) {
        return missionRepository.findFinishedMissions(baseMissionId, limit);
    }

    @Override
    public List<Mission> findAvailableMissionsByRegion(String region, Long baseMissionId, int limit) {
        return missionRepository.findAvailableMissionsByRegion(region, baseMissionId, limit);
    }
}