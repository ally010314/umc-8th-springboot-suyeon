package umc.spring.study.converter;

import umc.spring.study.domain.Review;
import umc.spring.study.domain.ReviewImage;

import java.util.List;

public class ReviewImageConverter {

    public static List<ReviewImage> toReviewImageList(List<String> imageUrls, Review review) {
        return imageUrls.stream()
                .map(url -> ReviewImage.builder()
                        .imageUrl(url)
                        .review(review)
                        .build())
                .toList();
    }
}
