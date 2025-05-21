package umc.spring.study.service.ReviewService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.study.apiPayload.code.status.ErrorStatus;
import umc.spring.study.apiPayload.exception.handler.ReviewHandler;
import umc.spring.study.converter.ReviewConverter;
import umc.spring.study.converter.ReviewImageConverter;
import umc.spring.study.domain.Review;
import umc.spring.study.domain.ReviewImage;
import umc.spring.study.domain.Store;
import umc.spring.study.repository.ReviewImageRepository.ReviewImageRepository;
import umc.spring.study.repository.ReviewRepository.ReviewRepository;
import umc.spring.study.repository.StoreRepository.StoreRepository;
import umc.spring.study.web.dto.ReviewRequestDTO;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Review joinReview(ReviewRequestDTO.JoinReviewDTO request) {

        if (request.getStars() == null || request.getStars() < 1 || request.getStars() > 5) {
            throw new ReviewHandler(ErrorStatus.INVALID_REVIEW_SCORE);
        }
        //store 있는지 검증
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new ReviewHandler(ErrorStatus.REVIEW_STORE_NOT_FOUND));

        // 리뷰 생성
        Review newReview = ReviewConverter.toReview(request, store);

        List<ReviewImage> reviewImageList =
                ReviewImageConverter.toReviewImageList(request.getImageUrl(), newReview);
        newReview.setReviewImageList(reviewImageList);

        return reviewRepository.save(newReview);
    }
}

