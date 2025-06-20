package umc.spring.study.repository.ReviewRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import umc.spring.study.domain.Review;
import umc.spring.study.domain.Store;
import umc.spring.study.repository.StoreRepository.StoreRepositoryCustom;

import java.util.List;

import static umc.spring.study.domain.QReview.review;
@RequiredArgsConstructor
@Repository
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Review> findAllByStore(Store store, PageRequest pageRequest) {
        List<Review> content = jpaQueryFactory
                .selectFrom(review)
                .where(review.store.eq(store))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .orderBy(review.createdAt.desc())
                .fetch();

        long total = jpaQueryFactory
                .selectFrom(review)
                .where(review.store.eq(store))
                .fetchCount();

        return new PageImpl<>(content, pageRequest, total);
    }

    @Override
    public Page<Review> findAllMyReview(Long memberId, PageRequest pageRequest) {
        List<Review> content = jpaQueryFactory
                .selectFrom(review)
                .where(review.member.id.eq(memberId))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .orderBy(review.createdAt.desc())
                .fetch();

        long total = jpaQueryFactory
                .selectFrom(review)
                .where(review.member.id.eq(memberId))
                .fetchCount();

        return new PageImpl<>(content, pageRequest, total);
    }
}
