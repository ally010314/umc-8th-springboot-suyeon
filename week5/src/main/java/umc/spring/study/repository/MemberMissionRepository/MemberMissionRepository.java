package umc.spring.study.repository.MemberMissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.study.domain.Food;
import umc.spring.study.domain.enums.MissionStatus;
import umc.spring.study.domain.mapping.MemberMission;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    Page<MemberMission> findByMemberIdAndStatus(Long memberId, MissionStatus status, Pageable pageable);  // ✅ Page 반환

}
