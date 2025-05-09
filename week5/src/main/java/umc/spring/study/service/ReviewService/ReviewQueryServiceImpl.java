package umc.spring.study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.domain.Review;
import umc.spring.study.repository.ReviewRepository.ReviewRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;


    @Override
    @Transactional
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

}
