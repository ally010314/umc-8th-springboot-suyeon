package umc.spring.study.service.ReviewService;

import org.springframework.data.domain.Page;
import umc.spring.study.domain.Review;

public interface ReviewQueryService {
    Review createReview(Review review);

    Page<Review> getReviewList(Long StoreId , Integer page);
    Page<Review> getMyReviewList(Long memberId, Integer page);
}
