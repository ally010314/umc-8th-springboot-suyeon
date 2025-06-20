package umc.spring.study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.Review;
import umc.spring.study.domain.Store;
import umc.spring.study.repository.MemberRepository.MemberRepository;
import umc.spring.study.repository.ReviewRepository.ReviewRepository;
import umc.spring.study.repository.StoreRepository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Page<Review> getReviewList(Long StoreId, Integer page) {
        Store store = storeRepository.findById(StoreId).get();
        Page<Review> StorePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return StorePage;
    }

    @Override
    public Page<Review> getMyReviewList(Long memberId, Integer page) {
        return reviewRepository.findAllMyReview(memberId, PageRequest.of(page, 10));
    }

}
