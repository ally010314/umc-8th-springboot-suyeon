package umc.spring.study.repository.MissionRepository;

import umc.spring.study.domain.Mission;
import umc.spring.study.domain.enums.MissionStatus;
import java.util.List;

public interface MissionRepositoryCustom {
    List<Mission> findRunningMissions(Long baseMissionId, int limit);
    List<Mission> findFinishedMissions(Long baseMissionId, int limit);
    List<Mission> findAvailableMissionsByRegion(String region, Long baseMissionId, int limit);
}