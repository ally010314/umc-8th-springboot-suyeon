package umc.spring.study.web.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class classJoinDTO {

    String name;
    Integer gender;
    Integer birthYear;
    Integer birthMonth;
    Integer birthDay;
    String address;
    List<Long> preferCategory;
}
