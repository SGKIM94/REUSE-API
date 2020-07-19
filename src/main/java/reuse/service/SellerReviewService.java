package reuse.service;

import org.springframework.stereotype.Service;
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

    public SellerReview create(CreateSellerReviewRequestView sellerReview, User requester) {
        Board board = boardService.findById(sellerReview.getBoardId());

        board.verifyThatUserAndRequester(requester, SalesStatusType.COMPLETE);

        return sellerReviewRepository.save(sellerReview.toEntity());
    }
}
