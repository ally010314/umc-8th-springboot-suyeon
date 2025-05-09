package umc.spring.study.service.MissionService;

import umc.spring.study.domain.Mission;
import umc.spring.study.domain.enums.MissionStatus;

import java.util.List;
import java.util.Optional;

public interface MissionQueryService {
    List<Mission> findRunningMissions(Long baseMissionId, int limit);
    List<Mission> findFinishedMissions(Long baseMissionId, int limit);
    List<Mission> findAvailableMissionsByRegion(String region, Long baseMissionId, int limit);
}
