package reuse.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reuse.domain.Board;
import reuse.domain.BuyerReview;
import reuse.domain.SalesStatusType;
import reuse.domain.User;
import reuse.dto.review.buyer.CreateBuyerReviewRequestView;
import reuse.dto.review.buyer.FindBuyerReviewResponseView;
import reuse.dto.review.buyer.ListBuyerReviewResponseView;
import reuse.repository.BuyerReviewRepository;

@Service
@Slf4j
public class BuyerReviewService {
    private BuyerReviewRepository buyerReviewRepository;
    private BoardService boardService;

    public BuyerReviewService(BuyerReviewRepository buyerReviewRepository, BoardService boardService) {
        this.buyerReviewRepository = buyerReviewRepository;
        this.boardService = boardService;
    }

    @Transactional
    public BuyerReview create(CreateBuyerReviewRequestView buyerReview, User buyer) {
        Board board = boardService.findById(buyerReview.getBoardId());

        board.verifyThatBoardCanChangeStatus(SalesStatusType.COMPLETE);
        board.verifyThatRequestAreTheSame(buyer.getSocialTokenId(), board.getBuyerTokenId());
        board.addScoreFromBuyerToSeller(buyerReview.getScore());

        BuyerReview review = buyerReviewRepository.save(buyerReview.toEntity(buyer));
        board.registerBuyerReview(review);

        return review;
    }

    @Transactional(readOnly = true)
    public ListBuyerReviewResponseView findBySeller(Long sellerId) {
        return buyerReviewRepository.findBySeller(sellerId);
    }

    @Transactional(readOnly = true)
    public ListBuyerReviewResponseView list() {
        return ListBuyerReviewResponseView.toDtoByEntity(buyerReviewRepository.findAll());
    }

    @Transactional
    public BuyerReview modify(BuyerReview buyerReview, Long id) {
        BuyerReview originalBuyerReview = findById(id);

        return originalBuyerReview.modify(buyerReview);
    }

    public BuyerReview findById(Long id) {
        return buyerReviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 구매후기가 존재하지 않습니다. : " + id));
    }

    @Transactional(readOnly = true)
    public FindBuyerReviewResponseView retrieve(Long id) {
        BuyerReview buyerReview = findById(id);
        return FindBuyerReviewResponseView.toDto(buyerReview);
    }
}
