package umc.spring.study.web.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

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
