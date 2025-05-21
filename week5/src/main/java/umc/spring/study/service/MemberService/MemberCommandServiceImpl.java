package umc.spring.study.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.apiPayload.code.status.ErrorStatus;
import umc.spring.study.apiPayload.exception.handler.MemberFoodPreferencesHandler;
import umc.spring.study.converter.MemberConverter;
import umc.spring.study.converter.MemberFoodPreferencesConverter;
import umc.spring.study.domain.Food;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.mapping.MemberFoodPreferences;
import umc.spring.study.repository.FoodRepository.FoodRepository;
import umc.spring.study.repository.MemberRepository.MemberRepository;
import umc.spring.study.web.dto.MemberRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;

    private final FoodRepository foodRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);
        List<Food> foodCategoryList = request.getPreferFood().stream()
                .map(food -> {
                    return foodRepository.findById(food).orElseThrow(() -> new MemberFoodPreferencesHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberFoodPreferences> memberPreferList = MemberFoodPreferencesConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }
}
