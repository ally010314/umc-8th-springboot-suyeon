package umc.spring.study.web.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import umc.spring.study.domain.enums.Role;

import java.time.LocalDateTime;
import java.util.List;

public class MissionRequestDTO {

    @Getter
    public static class CreateMissionDTO {

        @NotNull
        private Long storeId;

        @NotBlank
        private String content;

        @NotNull
        @Future
        private LocalDateTime deadline;

        @NotNull
        private Float point;
    }


    @Getter
    public static class ChallengeMissionRequestDTO {

        @NotNull
        private Long memberId;

        @NotNull
        private Long missionId;
    }


}
