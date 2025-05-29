package umc.spring.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import umc.spring.study.apiPayload.ApiResponse;
import umc.spring.study.converter.MissionConverter;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.Review;
import umc.spring.study.domain.mapping.MemberMission;
import umc.spring.study.service.MissionService.MissionCommandService;
import umc.spring.study.service.MissionService.MissionQueryService;
import umc.spring.study.web.dto.MissionRequestDTO;
import umc.spring.study.web.dto.MissionResponseDTO;



@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController{
    private final MissionQueryService missionQueryService;
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

    @GetMapping("/my-running")
    @Operation(summary = "진행 중인 미션 목록 조회 API", description = "memberId를 기준으로 현재 사용자가 도전 중인(RUNNING 상태) 미션 목록을 페이징 처리하여 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters(value = {
            @Parameter(name = "memberId", description = "회원 ID (쿼리스트링으로 전달)", required = true),
            @Parameter(name = "page", description = "페이지 번호 (0부터 시작, 기본값: 0)", required = false),
            @Parameter(name = "size", description = "페이지 당 항목 수 (기본값: 10)", required = false)
    })
    public ApiResponse<MissionResponseDTO.RunningMissionListDTO> getMyRunningMissions(
            @RequestParam(name = "memberId") Long memberId,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<MemberMission> missions = missionQueryService.findRunningMemberMissions(memberId, pageable);
        return ApiResponse.onSuccess(MissionConverter.toRunningMissionListDTO(missions));
    }




}