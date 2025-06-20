package umc.spring.study.converter;

import org.springframework.data.domain.Page;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.Store;
import umc.spring.study.domain.enums.MissionStatus;
import umc.spring.study.domain.mapping.MemberMission;
import umc.spring.study.web.dto.MissionRequestDTO;
import umc.spring.study.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public static MissionResponseDTO.RunningMissionDTO toRunningMissionDTO(MemberMission memberMission) {
        return MissionResponseDTO.RunningMissionDTO.builder()
                .missionId(memberMission.getMission().getId())
                .startedAt(memberMission.getCreatedAt())
                .build();
    }

    public static MissionResponseDTO.RunningMissionListDTO toRunningMissionListDTO(Page<MemberMission> memberMissions) {
        List<MissionResponseDTO.RunningMissionDTO> runningMissionDTOList = memberMissions.stream()
                .map(MissionConverter::toRunningMissionDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.RunningMissionListDTO.builder()
                .runningMissions(runningMissionDTOList)
                .listSize(runningMissionDTOList.size())
                .totalPage(memberMissions.getTotalPages())
                .totalElements(memberMissions.getTotalElements())
                .isFirst(memberMissions.isFirst())
                .isLast(memberMissions.isLast())
                .build();
    }


}
