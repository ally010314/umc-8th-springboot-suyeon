package umc.spring.study.repository.ReviewImageRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.study.domain.ReviewImage;

public interface ReviewImageRepository extends JpaRepository<ReviewImage,Long> {
}
