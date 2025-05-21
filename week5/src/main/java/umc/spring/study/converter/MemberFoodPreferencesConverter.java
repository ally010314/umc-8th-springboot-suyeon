package umc.spring.study.converter;

import umc.spring.study.domain.Food;
import umc.spring.study.domain.mapping.MemberFoodPreferences;

import java.util.List;
import java.util.stream.Collectors;

public class MemberFoodPreferencesConverter {

    public static List<MemberFoodPreferences> toMemberPreferList(List<Food> foodList){

        return foodList.stream()
                .map(food->
                        MemberFoodPreferences.builder()
                                .food(food)
                                .build()
                ).collect(Collectors.toList());
    }
}
