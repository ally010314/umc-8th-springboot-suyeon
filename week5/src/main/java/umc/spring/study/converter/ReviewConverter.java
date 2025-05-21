package umc.spring.study.converter;

import umc.spring.study.domain.Review;
import umc.spring.study.domain.Store;
import umc.spring.study.web.dto.ReviewRequestDTO;
import umc.spring.study.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReviewConverter {

    public static ReviewResponseDTO.JoinReviewResultDTO toJoinReviewResultDTO(Review review){
        return ReviewResponseDTO.JoinReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(ReviewRequestDTO.JoinReviewDTO request, Store store){
        return Review.builder()
                .body(request.getContent())
                .reviewImageList(new ArrayList<>())
                .score(request.getStars())
                .store(store)
                .build();
    }
}
