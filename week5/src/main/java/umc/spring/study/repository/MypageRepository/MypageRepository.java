package umc.spring.study.repository.MypageRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.study.domain.Member;

public interface MypageRepository extends JpaRepository<Member, Long>, MypageRepositoryCustom {
}
