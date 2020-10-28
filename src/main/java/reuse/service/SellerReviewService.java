package reuse.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reuse.domain.Board;
import reuse.domain.SalesStatusType;
import reuse.domain.SellerReview;
import reuse.domain.User;
import reuse.dto.review.seller.CreateSellerReviewRequestView;
import reuse.repository.SellerReviewRepository;

@Service
public class SellerReviewService {
    private SellerReviewRepository sellerReviewRepository;
    private BoardService boardService;

    public SellerReviewService(SellerReviewRepository sellerReviewRepository, BoardService boardService) {
        this.sellerReviewRepository = sellerReviewRepository;
        this.boardService = boardService;
    }

    @Transactional
    public SellerReview create(CreateSellerReviewRequestView sellerReview, User requester) {
        Board board = boardService.findById(sellerReview.getBoardId());

        board.verifyThatBoardCanChangeStatus(SalesStatusType.COMPLETE);
        board.verifyThatRequestAreTheSame(requester.getSocialTokenId(), board.getSellerTokenId());
        board.addScoreFromSellerToBuyer(sellerReview.getScore());

        SellerReview review = sellerReviewRepository.save(sellerReview.toEntity());
        board.registerSellerReview(review);

        return review;
    }
}
