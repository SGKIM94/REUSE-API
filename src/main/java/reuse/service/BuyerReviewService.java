package reuse.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reuse.domain.Board;
import reuse.domain.BuyerReview;
import reuse.domain.User;
import reuse.dto.review.buyer.CreateBuyerReviewRequestView;
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
        BuyerReview savedBuyerReview = buyerReviewRepository.save(buyerReview.toEntity(buyer));

        Board board = boardService.findById(buyerReview.getBoardId());
        board.mappingBuyerReview(savedBuyerReview);

        return buyerReviewRepository.save(buyerReview.toEntity(buyer));
    }

    public Board verifyBuyer(CreateBuyerReviewRequestView buyerReview, User buyer) {
        Board board = boardService.findById(buyerReview.getBoardId());

        if (board.getBuyerId() != buyer.getId()) {
            log.error("구매자의 ID : " + board.getBuyerId());
            log.error("로그인 사용자의 ID : " + buyer.getId());
            throw new IllegalArgumentException("해당 게시글의 구매자와 로그인 사용자와 일치하지 않습니다.");
        }

        return board;
    }

    public void verifyBoardStatus(Board board) {
        if (board.getSalesStatus() == null || !board.isCompleteSalesStatus()) {
            throw new IllegalArgumentException("거래 완료 상태가 아니기 때문에 후기를 작성할 수 없습니다. : " + board.getSalesStatus());
        }
    }

    public ListBuyerReviewResponseView findBySeller(Long sellerId) {
        return buyerReviewRepository.findBySeller(sellerId);
    }

    public ListBuyerReviewResponseView list() {
        return ListBuyerReviewResponseView.toDtoByEntity(buyerReviewRepository.findAll());
    }

    @Transactional
    public BuyerReview modify(BuyerReview buyerReview, Long id) {
        BuyerReview originalBuyerReview = retrieve(id);

        return originalBuyerReview.modify(buyerReview);
    }

    public BuyerReview retrieve(Long id) {
        return buyerReviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 구매후기가 존재하지 않습니다. : " + id));
    }
}
