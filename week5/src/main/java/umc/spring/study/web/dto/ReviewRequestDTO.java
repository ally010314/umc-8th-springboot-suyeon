package umc.spring.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

public class ReviewRequestDTO {

    @Getter
    public static class JoinReviewDTO{
        @NotBlank
        private String content;
        private List<String> imageUrl;
        @NotNull
        private Float stars;
        @NotNull
        private Long storeId;

    }
}
