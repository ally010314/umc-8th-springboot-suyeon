package umc.spring.study.converter;

import umc.spring.study.domain.Member;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.Store;
import umc.spring.study.domain.enums.MissionStatus;
import umc.spring.study.domain.mapping.MemberMission;
import umc.spring.study.web.dto.MissionRequestDTO;
import umc.spring.study.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;

public class MissionConverter {

    public static MissionResponseDTO.CreateMissionResultDTO toCreateMissionResultDTO(Mission mission) {
        return MissionResponseDTO.CreateMissionResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }


    public static Mission toMission(MissionRequestDTO.CreateMissionDTO request, Store store) {
        return Mission.builder()
                .content(request.getContent())
                .deadline(request.getDeadline())
                .point(request.getPoint())
                .store(store)
                .build();
    }

    public static MissionResponseDTO.ChallengeMissionResponseDTO toChallengeMissionResponseDTO(MemberMission memberMission) {
        return MissionResponseDTO.ChallengeMissionResponseDTO.builder()
                .memberId(memberMission.getMember().getId())
                .missionId(memberMission.getMission().getId())
                .status(memberMission.getStatus())  // MissionStatus
                .startedAt(memberMission.getCreatedAt())  // 혹은 mission.getCreatedAt()
                .build();
    }

    public static MemberMission toMemberMission(MissionRequestDTO.ChallengeMissionRequestDTO request, Member member, Mission mission, Store store) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .store(store)
                .status(MissionStatus.RUNNING)
                .build();
    }

}
