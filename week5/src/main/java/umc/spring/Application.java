package umc.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.Review;
import umc.spring.study.domain.Store;
import umc.spring.study.domain.enums.MissionStatus;
import umc.spring.study.service.MemberService.MemberQueryService;
import umc.spring.study.service.MissionService.MissionQueryService;
import umc.spring.study.service.MypageService.MypageQuerySerivce;
import umc.spring.study.service.ReviewService.ReviewQueryService;
import umc.spring.study.service.StoreService.StoreQueryService;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner run(ApplicationContext context) {
		return args -> {
			StoreQueryService storeService = context.getBean(StoreQueryService.class);
			MissionQueryService missionService = context.getBean(MissionQueryService.class);
			ReviewQueryService reviewService = context.getBean(ReviewQueryService.class);
			MemberQueryService memberService = context.getBean(MemberQueryService.class);

			// 파라미터 값 설정
			String name = "요아정";
			Float score = 4.0f;

			// 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
			System.out.println("Executing findStoresByNameAndScore with parameters:");
			System.out.println("Name: " + name);
			System.out.println("Score: " + score);

			storeService.findStoresByNameAndScore(name, score)
					.forEach(System.out::println);

			//여기서부터 실습


			Long baseMissionId = 3L;
			String region = "대현동";
			int limit = 15;

			missionService.findRunningMissions(baseMissionId, limit)
					.forEach(mission -> System.out.println("Running Mission ID: " + mission.getId() + ", Content: " + mission.getContent()));

			missionService.findFinishedMissions(baseMissionId, limit)
					.forEach(mission -> System.out.println("Finished Mission ID: " + mission.getId() + ", Content: " + mission.getContent()));

			missionService.findAvailableMissionsByRegion(region, baseMissionId, limit)
					.forEach(availableMission -> System.out.println("Available Mission ID: " + availableMission.getId() + ", Content: " + availableMission.getContent()));

			MypageQuerySerivce  myPageService = context.getBean(MypageQuerySerivce.class);
			Long baseMemberId = 3L;
			myPageService.selectMyInfo(baseMemberId).forEach(member->System.out.println("Member ID: " + member.getId() + ", Member name: " + member.getName()));

			Member member = memberService.findMember(baseMemberId)
					.orElseThrow(() -> new RuntimeException("Member not found"));

			Store store = storeService.findStore(1L)
					.orElseThrow(() -> new IllegalArgumentException("Store not found"));

			Review newReview = Review.builder()
					.member(member)
					.store(store)
					.title("제목")
					.body("맛있었음")
					.score(5.0F)
					.build();

			reviewService.createReview(newReview);
			System.out.println("리뷰 저장 완료: " + newReview.getBody());


		};
	}

}
