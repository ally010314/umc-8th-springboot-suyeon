package umc.spring.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.study.apiPayload.ApiResponse;
import umc.spring.study.converter.MissionConverter;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.Review;
import umc.spring.study.domain.mapping.MemberMission;
import umc.spring.study.service.MissionService.MissionCommandService;
import umc.spring.study.web.dto.MissionRequestDTO;
import umc.spring.study.web.dto.MissionResponseDTO;



@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController{
    private final MissionCommandService missionCommandService;

    @PostMapping("/")
    public ApiResponse<MissionResponseDTO.CreateMissionResultDTO> createMission(@RequestBody @Valid MissionRequestDTO.CreateMissionDTO request) {
        Mission mission = missionCommandService.createMission(request);
        return ApiResponse.onSuccess(MissionConverter.toCreateMissionResultDTO(mission));
    }

    @PostMapping("/challenge")
    public ApiResponse<MissionResponseDTO.ChallengeMissionResponseDTO> challengeMission(@RequestBody @Valid MissionRequestDTO.ChallengeMissionRequestDTO request) {
        MemberMission memberMission = missionCommandService.challengeMission(request);
        return ApiResponse.onSuccess(MissionConverter.toChallengeMissionResponseDTO(memberMission));
    }


}