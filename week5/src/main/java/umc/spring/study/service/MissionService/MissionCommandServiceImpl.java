package umc.spring.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.apiPayload.code.status.ErrorStatus;
import umc.spring.study.apiPayload.exception.handler.MemberHandler;
import umc.spring.study.apiPayload.exception.handler.ReviewHandler;
import umc.spring.study.apiPayload.exception.handler.StoreHandler;
import umc.spring.study.converter.MissionConverter;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.Store;
import umc.spring.study.domain.mapping.MemberMission;
import umc.spring.study.repository.MemberMissionRepository.MemberMissionRepository;
import umc.spring.study.repository.MemberRepository.MemberRepository;
import umc.spring.study.repository.MissionRepository.MissionRepository;
import umc.spring.study.repository.StoreRepository.StoreRepository;
import umc.spring.study.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService{
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;  // 이미 있음

    @Override
    @Transactional
    public Mission createMission(MissionRequestDTO.CreateMissionDTO request) {
        //store 있는지 검증
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new StoreHandler(ErrorStatus.MISSION_STORE_NOT_FOUND));

        Mission mission = MissionConverter.toMission(request, store);

        return missionRepository.save(mission);
    }

    @Transactional
    @Override
    public MemberMission challengeMission(MissionRequestDTO.ChallengeMissionRequestDTO request) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new ReviewHandler(ErrorStatus.MISSION_STORE_NOT_FOUND));  // or MissionHandler

        Store store = mission.getStore();

        MemberMission memberMission = MissionConverter.toMemberMission(request, member, mission, store);

        return memberMissionRepository.save(memberMission);
    }


}
