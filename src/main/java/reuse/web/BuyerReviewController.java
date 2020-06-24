package reuse.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reuse.domain.BuyerReview;
import reuse.domain.User;
import reuse.dto.review.buyer.CreateBuyerReviewRequestView;
import reuse.security.LoginUser;
import reuse.service.BuyerReviewService;

@RestController
@RequestMapping("/review/buyer")
public class BuyerReviewController {
    private BuyerReviewService buyerReviewService;

    public BuyerReviewController(BuyerReviewService buyerReviewService) {
        this.buyerReviewService = buyerReviewService;
    }

    @PostMapping("")
    public ResponseEntity create(@RequestBody CreateBuyerReviewRequestView board, @LoginUser User buyer) {
        BuyerReview buyerReview = buyerReviewService.create(board, buyer);
        return ResponseEntity.ok().body(buyerReview);
    }
}
