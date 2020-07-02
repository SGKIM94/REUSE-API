package reuse.service;

import org.springframework.stereotype.Service;
import reuse.domain.Board;
import reuse.domain.BuyerReview;
import reuse.domain.User;
import reuse.dto.review.buyer.CreateBuyerReviewRequestView;
import reuse.dto.review.buyer.ListBuyerReviewResponseView;
import reuse.repository.BuyerReviewRepository;

@Service
public class BuyerReviewService {
    private BuyerReviewRepository buyerReviewRepository;
    private BoardService boardService;

    public BuyerReviewService(BuyerReviewRepository buyerReviewRepository, BoardService boardService) {
        this.buyerReviewRepository = buyerReviewRepository;
        this.boardService = boardService;
    }

    public BuyerReview create(CreateBuyerReviewRequestView buyerReview, User buyer) {
        BuyerReview savedBuyerReview = buyerReviewRepository.save(buyerReview.toEntity(buyer));

        Board board = boardService.findById(buyerReview.getBoardId());
        board.mappingBuyerReview(savedBuyerReview);

        return buyerReviewRepository.save(buyerReview.toEntity(buyer));
    }

    public ListBuyerReviewResponseView findBySeller(Long sellerId) {
        return buyerReviewRepository.findBySeller(sellerId);
    }

    public ListBuyerReviewResponseView list() {
        return ListBuyerReviewResponseView.toDtoByEntity(buyerReviewRepository.findAll());
    }

    public void modify(BuyerReview buyerReview) {

    }
}
