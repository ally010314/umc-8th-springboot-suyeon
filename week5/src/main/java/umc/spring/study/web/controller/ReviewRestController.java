package umc.spring.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.study.apiPayload.ApiResponse;
import umc.spring.study.converter.ReviewConverter;
import umc.spring.study.domain.Review;
import umc.spring.study.service.ReviewService.ReviewCommandService;
import umc.spring.study.service.ReviewService.ReviewQueryService;
import umc.spring.study.validation.annotation.ExistStore;
import umc.spring.study.validation.annotation.ValidPage;
import umc.spring.study.web.dto.ReviewRequestDTO;
import umc.spring.study.web.dto.ReviewResponseDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @PostMapping("/")
    public ApiResponse<ReviewResponseDTO.JoinReviewResultDTO> join(@RequestBody @Valid ReviewRequestDTO.JoinReviewDTO request){
        Review review = reviewCommandService.joinReview(request);
        return ApiResponse.onSuccess(ReviewConverter.toJoinReviewResultDTO(review));
    }

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name="storeId") Long storeId, @RequestParam(name="page") Integer page){
        Page<Review> reviewList = reviewQueryService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(ReviewConverter.reviewPreViewListDTO(reviewList));

    }

    @GetMapping("/myReviews")
    @Operation(summary = "내 리뷰 목록 조회 API", description = "내가 쓴 리뷰들의 목록을 조회하는 API입니다. 페이징 포함, query String으로 memberId와 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "유저 ID, query string 으로 전달"),
            @Parameter(name = "page", description = "페이지 번호, query string 으로 전달 (1부터 시작)")
    })
    public ApiResponse<ReviewResponseDTO.MyReviewListDTO> getMyReviewList(
            @RequestParam(name = "memberId") Long memberId,
            @RequestParam(name = "page") @Valid @ValidPage Integer page
    ) {
        Page<Review> myReviewList = reviewQueryService.getMyReviewList(memberId, page);
        return ApiResponse.onSuccess(ReviewConverter.myReviewListDTO(myReviewList));
    }


}
